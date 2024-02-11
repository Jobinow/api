package com.jobinow.services.impl;

import com.jobinow.exceptions.ResourceNotCreatedException;
import com.jobinow.model.dto.requests._Request;
import com.jobinow.model.dto.responses._Response;
import com.jobinow.model.entities._Entity;
import com.jobinow.mapper._Mapper;
import com.jobinow.services.spec._Service;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

/**
 * Generic service implementation with common CRUD operations.
 *
 * @param <Req>        The request DTO type.
 * @param <Res>        The response DTO type.
 * @param <Entity>     The entity type.
 * @param <Repository> The repository type extending JpaRepository<Entity, UUID>.
 * @param <Mapper>     The mapper type implementing _Mapper<Req, Res, Entity>.
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Slf4j
@Validated
@AllArgsConstructor
@NoArgsConstructor(force = true)
@CacheConfig(cacheNames = "EntityCache")
public abstract class _ServiceImp<ID, Req extends _Request, Res extends _Response<ID>, Entity extends _Entity<ID>, Repository extends JpaRepository<Entity, ID>, Mapper extends _Mapper<ID, Req, Res, Entity>> implements _Service<ID, Req, Res> {

    Mapper mapper;
    Repository repository;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public final void setRepository(Repository repository) {
        this.repository = repository;
    }

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public final void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Retrieves a list of all entities.
     *
     * @return List of response DTOs representing all entities.
     */
    @Transactional
    @Cacheable(sync = true)
    public List<Res> getAll() {
        assert repository != null;
        assert mapper != null;
        return mapper.toResponse(
                repository.findAll()
        );
    }

    /**
     * Retrieves all entities in a paginated form.
     *
     * @param pageable Pagination information.
     * @return Page of response DTOs.
     */
    @Transactional
    public Page<Res> getAll(Pageable pageable) {
        assert repository != null;
        assert mapper != null;
        return repository.findAll(pageable)
                .map(mapper::toResponse);
    }

    /**
     * Creates a new entity based on the provided request DTO.
     *
     * @param request DTO containing data for entity creation.
     * @return Optional containing the response DTO of the created entity.
     */
    @Caching(
            evict = {
                    @CacheEvict(
                            key = "#result.get().getId()",
                            allEntries = true, condition = "#result.get().id != null"
                    )
            }
    )
    @Transactional
    public Optional<Res> create(@Valid Req request) {
        assert mapper != null;
        Entity entityToCreate = mapper.toEntityFromRequest(request);
        try {
            assert repository != null;
            Entity createdEntity = repository.saveAndFlush(entityToCreate);
            return Optional.of(mapper.toResponse(createdEntity));
        } catch (Exception e) {
            log.error("Error while creating entity", e);
            throw new ResourceNotCreatedException(e.getMessage());
        }
    }

    /**
     * Updates an existing entity based on the provided response DTO.
     *
     * @param response DTO containing updated data.
     * @return Optional containing the response DTO of the updated entity.
     */
    @CachePut(
            value = "file",
            key = "#response.id"
    )
    @Transactional
    public Optional<Res> update(@Valid Res response) {
        assert mapper != null;
        Entity entityToUpdate = mapper.toEntityFromResponse(response);
        try {
            assert repository != null;
            Entity updatedEntity = repository.saveAndFlush(entityToUpdate);
            return Optional.of(mapper.toResponse(updatedEntity));
        } catch (Exception e) {
            log.error("Error while updating entity", e);
            throw new ResourceNotCreatedException(e.getMessage());
        }
    }

    /**
     * Retrieves an entity by its unique identifier.
     *
     * @param id Unique identifier of the entity.
     * @return Optional containing the response DTO of the found entity.
     */
    @Transactional
    @Cacheable(key = "#id", sync = true)
    public Optional<Res> getById(ID id) {
        assert repository != null;
        assert mapper != null;
        return repository.findById(id)
                .map(mapper::toResponse);
    }

    /**
     * Deletes an entity based on the provided response DTO.
     *
     * @param response DTO containing data for entity deletion.
     * @return Boolean indicating the success of the deletion operation.
     */
    @Transactional
    @CacheEvict(
            key = "#response.id",
            allEntries = true
    )
    public Boolean delete(@Valid Res response) {
        assert mapper != null;
        Entity entityToDelete = mapper.toEntityFromResponse(response);
        assert repository != null;
        if (!repository.existsById(entityToDelete.getId())) {
            return false;
        }
        try {
            repository.delete(entityToDelete);
        } catch (Exception e) {
            log.error("Error while deleting entity", e);
            throw new ResourceNotCreatedException(e.getMessage());
        }
        return true;
    }
}