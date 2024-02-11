package com.jobinow.services.impl;

import com.jobinow.exceptions.ResourceException;
import com.jobinow.model.dto.basic.ApplicationStatistics;
import com.jobinow.model.dto.requests.ApplyRequest;
import com.jobinow.model.dto.responses.ApplyResponse;
import com.jobinow.model.dto.responses.OfferResponse;
import com.jobinow.model.dto.responses.UserResponses;
import com.jobinow.model.entities.Apply;
import com.jobinow.model.enums.ApplyType;
import com.jobinow.mapper.ApplyMapper;
import com.jobinow.mapper.OfferMapper;
import com.jobinow.mapper.UserMapper;
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
@AllArgsConstructor
@CacheConfig(cacheNames = "apply")
public class ApplyServiceImpl extends _ServiceImp<UUID, ApplyRequest, ApplyResponse, Apply, ApplyRepository, ApplyMapper> implements ApplyService {
    private final UserMapper userMapper;
    private final OfferMapper offerMapper;

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

    /**
     * Retrieves a list of all job applications for the specified job offer filtered by application type.
     *
     * @param offerResponse The job offer for which to retrieve applications.
     * @param applyType application type to use in filtration
     * @return A list of all job applications for the specified job offer.
     */
    @Override
    public List<ApplyResponse> getAppliesByApplyType(OfferResponse offerResponse, ApplyType applyType) {
        return mapper.toResponse(
                repository.getAppliesByOfferAndApplyType(
                        offerMapper.toEntityFromResponse(offerResponse),
                        applyType
                )
        );
    }

    /**
     * Retrieves a list of all job applications for the specified job offer.
     *
     * @param offerResponse The job offer for which to retrieve applications.
     * @return A list of all job applications for the specified job offer.
     */
    @Override
    public List<ApplyResponse> getOfferApplies(OfferResponse offerResponse) {
        return mapper.toResponse(
                repository.getAppliesByOffer(
                        offerMapper.toEntityFromResponse(offerResponse)
                )
        );
    }

    /**
     * Update candidate application status to be seen, accepted or refused.
     *
     * @param applyId The application to be updated.
     */
    @Override
    public void updateApplyStatus(String applyId, ApplyResponse applyResponse) {
        repository.updateApplyStatus(
                applyResponse.getStatus(),
                this.toUuidFromString(applyId)
        );
    }

    /**
     * Retrieve a list of type Object[] from the repository query method, then it cast it to a list of type ApplicationStatistics (using custom constructor)
     * @param applyId The application id which needs statistics.
     * @return list of ApplicationStatistics;
     */
    @Override
    public List<ApplicationStatistics> getApplicationStatistics(String applyId) {
        List<Object[]> plainApplicationStatistics = repository.getApplicationStatistics(this.toUuidFromString(applyId));
        return plainApplicationStatistics.stream()
                .map(ApplicationStatistics::new)
                .toList();
    }

    private UUID toUuidFromString(String stringId) {
        try { return UUID.fromString(stringId); }
        catch (IllegalArgumentException e) { throw new ResourceException("Please enter a valid UUID format"); }
    }
}