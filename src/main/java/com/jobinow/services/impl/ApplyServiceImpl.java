package com.jobinow.services.impl;

import com.jobinow.model.dto.requests.ApplyRequest;
import com.jobinow.model.dto.responses.ApplyResponse;
import com.jobinow.model.dto.responses.UserResponses;
import com.jobinow.model.entities.Apply;
import com.jobinow.model.mapper.ApplyMapper;
import com.jobinow.model.mapper.UserMapper;
import com.jobinow.repositories.ApplyRepository;
import com.jobinow.services.spec.ApplyService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of the {@link ApplyService} interface providing functionality for applying to job offers.
 * This class extends the generic service implementation {@link _ServiceImp}.
 *
 * @see ApplyService
 * @see _ServiceImp
 */
@Service
@CacheConfig(cacheNames = "apply")
@AllArgsConstructor
public class ApplyServiceImpl extends _ServiceImp<UUID, ApplyRequest, ApplyResponse, Apply, ApplyRepository, ApplyMapper> implements ApplyService {
    private final UserMapper userMapper;

    /**
     * Retrieves a paginated list of all job applications for the specified job seeker.
     *
     * @param jobSeeker The job seeker for which to retrieve applications.
     * @param pageable  The pagination information.
     * @return A paginated list of all job applications for the specified job seeker.
     */
    public Page<ApplyResponse> getAllApplies(UserResponses jobSeeker, Pageable pageable) {
        return repository.findAllByJobSeeker(
                        userMapper.toEntityFromResponse(jobSeeker),
                        pageable
                )
                .map(mapper::toResponse);
    }

    /**
     * Retrieves a list of all job applications for the specified job seeker.
     *
     * @param jobSeeker The job seeker for which to retrieve applications.
     * @return A list of all job applications for the specified job seeker.
     */
    public List<ApplyResponse> getAllApplies(UserResponses jobSeeker) {
        return mapper.toResponse(
                repository.findAllByJobSeeker(userMapper.toEntityFromResponse(jobSeeker))
        );
    }
}