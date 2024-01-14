package com.jobinow.services.spec;

import com.jobinow.model.dto.basic.ApplicationStatistics;
import com.jobinow.model.dto.requests.ApplyRequest;
import com.jobinow.model.dto.responses.ApplyResponse;
import com.jobinow.model.dto.responses.OfferResponse;
import com.jobinow.model.dto.responses.UserResponses;
import com.jobinow.model.enums.ApplyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing job applications.
 * This service extends the generic service interface {@link _Service},
 * specializing in operations related to applying for job positions.
 * It defines methods for handling the lifecycle of job applications,
 * including creation, retrieval, and response generation.
 * <p>
 * The interface is designed to work with UUID as the identifier type for apply entities.
 * </p>
 *
 * @version 1.0
 * @see ApplyRequest
 * @see ApplyResponse
 * @see _Service
 */
public interface ApplyService extends _Service<UUID, ApplyRequest, ApplyResponse> {
    /**
     * Retrieves a paginated list of all job applications for the specified job seeker.
     *
     * @param jobSeeker The job seeker for which to retrieve applications.
     * @param pageable  The pagination information.
     * @return A paginated list of all job applications for the specified job seeker.
     */
    Page<ApplyResponse> getAllApplies(UserResponses jobSeeker, Pageable pageable);

    /**
     * Retrieves a list of all job applications for the specified job seeker.
     *
     * @param jobSeeker The job seeker for which to retrieve applications.
     * @return A list of all job applications for the specified job seeker.
     */
    List<ApplyResponse> getAllApplies(UserResponses jobSeeker);

    /**
     * Retrieves a list of all job applications for the specified job offer filtered by application type.
     *
     * @param offerResponse The job offer for which to retrieve applications.
     * @param applyType application type to use in filtration
     * @return A list of all job applications for the specified job offer.
     */
    List<ApplyResponse> getAppliesByApplyType(OfferResponse offerResponse, ApplyType applyType);

    /**
     * Retrieves a list of all job applications for the specified job offer.
     *
     * @param offerResponse The job offer for which to retrieve applications.
     * @return A list of all job applications for the specified job offer.
     */
    List<ApplyResponse> getOfferApplies(OfferResponse offerResponse);

    /**
     * Update candidate application status to be seen, accepted or refused.
     *
     * @param applyResponse The application to be updated.
     */
    void updateApplyStatus(String applyId, ApplyResponse applyResponse);

    /**
     * retrieve statistics about job applications for a certain offer (application date and applications count).
     *
     * @param applyId The application id which needs statistics.
     * @return list of ApplicationStatistics;
     */
    List<ApplicationStatistics> getApplicationStatistics(String applyId);
}