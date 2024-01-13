package com.jobinow.model.entities;

import com.jobinow.model.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Entity class representing a subscription made by a recruiter to a specific pack.
 */
@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Subscription extends AbstractEntity {
    /**
     * The subscription pack associated with this subscription.
     */
    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = Pack.class
    )
    private Pack pack;

    /**
     * The recruiter who made the subscription.
     */
    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = User.class
    )
    private User recruiter;

    /**
     * The status of the subscription, indicating whether it is active or terminated.
     */
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status = SubscriptionStatus.ACTIVE;

    /**
     * Callback method executed before the entity is persisted.
     * It ensures that the default status is set to ACTIVE if not provided during creation.
     */
    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = SubscriptionStatus.ACTIVE;
        }
    }
}