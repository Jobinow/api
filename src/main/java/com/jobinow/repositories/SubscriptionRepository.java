package com.jobinow.repositories;

import com.jobinow.model.entities.Subscription;
import com.jobinow.model.entities.User;
import com.jobinow.model.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository for the {@link Subscription} entity.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
    /**
     * Retrieve the latest subscription for a given recruiter with a specified status.
     *
     * @param recruiterId The unique identifier of the recruiter associated with the subscription.
     * @param status      The status of the subscription to filter by.
     * @return An {@link Optional} containing the latest subscription for the recruiter with the specified status,
     *         or an empty {@link Optional} if no matching subscription is found.
     */
    Optional<Subscription> findFirstByRecruiterAndStatusOrderByCreatedAtDesc(User recruiterId, SubscriptionStatus status);
}