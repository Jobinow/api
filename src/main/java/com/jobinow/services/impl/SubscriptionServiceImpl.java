package com.jobinow.services.impl;

import com.jobinow.exceptions.InvalidPaymentException;
import com.jobinow.model.dto.requests.ChargeRequest;
import com.jobinow.model.dto.requests.SubscriptionRequest;
import com.jobinow.model.dto.responses.SubscriptionResponse;
import com.jobinow.model.entities.Subscription;
import com.jobinow.model.entities.User;
import com.jobinow.model.enums.SubscriptionStatus;
import com.jobinow.mapper.SubscriptionMapper;
import com.jobinow.repositories.SubscriptionRepository;
import com.jobinow.services.spec.StripeService;
import com.jobinow.services.spec.SubscriptionService;
import com.stripe.model.Charge;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Service implementation class for managing Subscription.
 * This service provides methods for creating, updating, and retrieving operations related to {@link Subscription}.
 * It extends the generic service implementation ({@link _ServiceImp}) and leverages the {@link SubscriptionMapper}
 * for mapping between entities and DTOs.
 *
 * @version 1.0
 * @see _ServiceImp
 * @see Subscription
 * @see SubscriptionRequest
 * @see SubscriptionResponse
 * @see SubscriptionMapper
 * @see SubscriptionRepository
 */
@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "Subscription")
public class SubscriptionServiceImpl extends _ServiceImp<UUID, SubscriptionRequest, SubscriptionResponse, Subscription, SubscriptionRepository, SubscriptionMapper> implements SubscriptionService {

    private final StripeService stripeService;

    /**
     * Creates a new entity based on the provided request DTO.
     *
     * @param request DTO containing data for entity creation.
     * @return Optional containing the response DTO of the created entity.
     */
    @Override
    @PreAuthorize("hasRole('SUPER_ADMINISTRATOR')")
    public Optional<SubscriptionResponse> create(SubscriptionRequest request) {
        return super.create(request);
    }

    /**
     * Updates an existing entity based on the provided response DTO.
     *
     * @param response DTO containing updated data.
     * @return Optional containing the response DTO of the updated entity.
     */
    @Override
    @PreAuthorize("hasRole('SUPER_ADMINISTRATOR')")
    public Optional<SubscriptionResponse> update(SubscriptionResponse response) {
        return super.update(response);
    }

    /**
     * Deletes an entity based on the provided response DTO.
     *
     * @param response DTO containing data for entity deletion.
     * @return Boolean indicating the success of the deletion operation.
     */
    @Override
    @PreAuthorize("hasRole('SUPER_ADMINISTRATOR')")
    public Boolean delete(SubscriptionResponse response) {
        return super.delete(response);
    }

    /**
     * Creates a new subscription with payment processing.
     *
     * @param request        DTO containing data for entity creation.
     * @param chargeRequest  DTO containing payment details.
     * @return Optional containing the response DTO of the created entity.
     * @throws InvalidPaymentException If payment processing fails.
     */
    public Optional<SubscriptionResponse> create(SubscriptionRequest request, ChargeRequest chargeRequest) {
        Charge charge = stripeService.charge(chargeRequest);
        if (charge.getPaid()) {
            return super.create(request);
        } else {
            throw new InvalidPaymentException(charge.getFailureMessage());
        }
    }

    /**
     * Finds a subscription by the recruiter.
     *
     * @param recruiter The recruiter to search by.
     * @return The subscription found.
     */
    public Optional<SubscriptionResponse> findByRecruiter(User recruiter) {
        SubscriptionResponse subscriptionResponse = mapper.toResponse(
                repository.findFirstByRecruiterAndStatusOrderByCreatedAtDesc(
                                recruiter,
                                SubscriptionStatus.ACTIVE
                        )
                        .orElse(null)
        );
        return Optional.ofNullable(subscriptionResponse);
    }
}