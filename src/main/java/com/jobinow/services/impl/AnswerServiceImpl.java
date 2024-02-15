package com.jobinow.services.impl;

import com.jobinow.exceptions.NoAuthenticateUser;
import com.jobinow.exceptions.ResourceNotCreatedException;
import com.jobinow.exceptions.ResourceNotFoundException;
import com.jobinow.mapper.AnswerMapper;
import com.jobinow.model.dto.requests.AnswerRequestDto;
import com.jobinow.model.dto.responses.AnswerResponsesDto;
import com.jobinow.model.entities.Answer;
import com.jobinow.model.entities.Assessment;
import com.jobinow.model.entities.User;
import com.jobinow.repositories.AnswerRepository;
import com.jobinow.services.spec.AnswerService;
import com.jobinow.services.spec.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the AnswerService interface providing CRUD operations for {@link Answer} entities.
 * It extends _ServiceImp and uses AnswerRepository for data access and AnswerMapper for entity-DTO mapping.
 * <p>
 * The service handles the business logic associated with Answer entities, such as associating the authenticated
 * user with new answers.
 */
@Slf4j
@Service
@AllArgsConstructor
@CacheConfig(cacheNames = "answer")
public class AnswerServiceImpl extends _ServiceImp<UUID, AnswerRequestDto, AnswerResponsesDto, Answer, AnswerRepository, AnswerMapper> implements AnswerService {
    private UserService userService;

    /**
     * Creates a new Answer entity based on the provided DTO and associates it with the currently authenticated user.
     *
     * @param request DTO containing data for creating a new Answer entity.
     * @return An Optional containing the response DTO of the created Answer entity.
     * @throws ResourceNotCreatedException if the user is not authenticated or cannot be found.
     */
    @Override
    @Transactional
    public Optional<AnswerResponsesDto> create(AnswerRequestDto request) {
        Answer entityToCreate = mapper.toEntityFromRequest(request);
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken)
            throw new NoAuthenticateUser("User not authenticated");

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        entityToCreate.setUser(user);

        try {
            Answer createdEntity = repository.saveAndFlush(entityToCreate);
            return Optional.of(mapper.toResponse(createdEntity));
        } catch (Exception e) {
            log.error("Error while creating entity", e);
            throw new ResourceNotCreatedException(e.getMessage());
        }
    }

    public List<Answer> findByAssessment(Assessment assessment) {
        return repository.findByAssessment(assessment);
    }
}