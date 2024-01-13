package com.jobinow.services.impl;

import com.jobinow.model.dto.requests.SubscriptionRequest;
import com.jobinow.model.dto.responses.SubscriptionResponse;
import com.jobinow.model.entities.Subscription;
import com.jobinow.model.entities.User;
import com.jobinow.model.enums.SubscriptionStatus;
import com.jobinow.model.mapper.SubscriptionMapper;
import com.jobinow.repositories.SubscriptionRepository;
import com.jobinow.services.spec.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Service implementation class for managing Subscription.
 * This service provides methods for creating, updating, and retrieving operations related to {@link Subscription}.
 * It extends the generic service implementation ({@link _ServiceImp}) and leverages the {@link SubscriptionMapper}
 * for mapping between entities and DTOs.
 * <p>
 * The class handles operations such as creating, updating, and retrieving Subscription, using the specified
 * repository ({@link SubscriptionRepository}) for data access.
 * </p>
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
        return subscriptionResponse == null ?
                Optional.empty() :
                Optional.of(subscriptionResponse);
    }
}