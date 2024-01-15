package com.jobinow.services.spec;

import com.jobinow.exceptions.InvalidPaymentException;
import com.jobinow.model.dto.requests.ChargeRequest;
import com.jobinow.model.dto.requests.SubscriptionRequest;
import com.jobinow.model.dto.responses.SubscriptionResponse;
import com.jobinow.model.entities.User;

import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for managing Subscription.
 * This service extends the generic service interface {@link _Service},
 * specializing in operations related to tag entities.
 * It defines methods for creating, retrieving, and handling
 * other aspects of tag data.
 * <p>
 * The interface is designed to work with UUID as the identifier type for tag entities.
 * </p>
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 * @version 1.0
 * @see SubscriptionRequest
 * @see SubscriptionResponse
 * @see _Service
 */
public interface SubscriptionService extends _Service<UUID, SubscriptionRequest, SubscriptionResponse> {
    /**
     * Creates a new subscription with payment processing.
     *
     * @param request       DTO containing data for entity creation.
     * @param chargeRequest DTO containing payment details.
     * @return Optional containing the response DTO of the created entity.
     * @throws InvalidPaymentException If payment processing fails.
     */
    Optional<SubscriptionResponse> create(SubscriptionRequest request, ChargeRequest chargeRequest);

    /**
     * Finds a subscription by the recruiter.
     *
     * @param recruiter The recruiter to search by.
     * @return The subscription found.
     */
    Optional<SubscriptionResponse> findByRecruiter(User recruiter);
}