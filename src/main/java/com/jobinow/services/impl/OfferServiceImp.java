package com.jobinow.services.impl;

import com.jobinow.exceptions.NotAllowedToCreateOfferException;
import com.jobinow.model.dto.requests.OfferRequest;
import com.jobinow.model.dto.responses.OfferResponse;
import com.jobinow.model.dto.responses.SubscriptionResponse;
import com.jobinow.model.entities.Offer;
import com.jobinow.model.entities.User;
import com.jobinow.mapper.OfferMapper;
import com.jobinow.mapper.UserMapper;
import com.jobinow.repositories.OfferRepository;
import com.jobinow.services.spec.OfferService;
import com.jobinow.services.spec.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Service implementation class for managing job offers.
 * This service provides methods for creating, updating, and retrieving operations related to {@link Offer}.
 * It extends the generic service implementation ({@link _ServiceImp}) and leverages the {@link OfferMapper}
 * for mapping between entities and DTOs.
 * <p>
 * The class handles operations such as creating, updating, and retrieving job offers, using the specified
 * repository ({@link OfferRepository}) for data access.
 * </p>
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">ouharri outman</a>
 * @version 1.0
 * @see _ServiceImp
 * @see Offer
 * @see OfferRequest
 * @see OfferResponse
 * @see OfferMapper
 * @see OfferRepository
 */
@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "offer")
public class OfferServiceImp extends _ServiceImp<UUID, OfferRequest, OfferResponse, Offer, OfferRepository, OfferMapper> implements OfferService {
    private final UserMapper userMapper;
    private final SubscriptionService subscriptionService;

    /**
     * Creates a new job offer with additional logic to check the recruiter's subscription limits
     * and the maximum number of allowed offers.
     *
     * @param request The {@link OfferRequest} containing details for the offer creation.
     * @return An {@link Optional} containing the created {@link OfferResponse} if successful,
     * or an empty {@link Optional} if the creation is not allowed based on the specified conditions.
     */
    @Override
    public Optional<OfferResponse> create(OfferRequest request) {
        User recruiter = userMapper.toEntityFromResponse(request.recruiter());
        Optional<SubscriptionResponse> subscription = subscriptionService.findByRecruiter(recruiter);

        if (subscription.isPresent()) {
            if (subscription.get().getPack().isUnlimitedOffers() || canCreateMoreOffers(subscription, recruiter)) {
                return super.create(request);
            } else {
                log.error("The recruiter {} has reached the maximum number of offers allowed", recruiter.getId());
                throw new NotAllowedToCreateOfferException();
            }
        } else {
            if (canCreateMoreOffersWithoutSubscription(recruiter)) {
                return super.create(request);
            } else {
                log.error("The recruiter {} has reached the maximum number of offers allowed", recruiter.getId());
                throw new NotAllowedToCreateOfferException();
            }
        }
    }

    /**
     * Checks if the recruiter can create more offers based on the subscription limits.
     *
     * @param subscription The {@link SubscriptionResponse} representing the recruiter's subscription.
     * @param recruiter    The {@link User} representing the recruiter.
     * @return {@code true} if the recruiter can create more offers, {@code false} otherwise.
     */
    private boolean canCreateMoreOffers(Optional<SubscriptionResponse> subscription, User recruiter) {
        return repository.countAllByRecruiter(recruiter) < subscription.get().getPack().getNbOffers();
    }

    /**
     * Checks if the recruiter can create more offers without an active subscription.
     *
     * @param recruiter The {@link User} representing the recruiter.
     * @return {@code true} if the recruiter can create more offers, {@code false} otherwise.
     */
    private boolean canCreateMoreOffersWithoutSubscription(User recruiter) {
        return repository.countAllByRecruiter(recruiter) < 3;
    }

}