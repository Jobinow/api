package com.jobinow.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Represents the association of a badge with a user and an assessment.
 * It links a badge to a user and the specific assessment for which the badge was awarded.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserBadges extends AbstractEntity {

    /**
     * The badge awarded to the user.
     */
    @OneToOne
    @NotNull(message = "Badge cannot be null")
    private Badge badge;

    /**
     * The user who earned the badge.
     */
    @OneToOne
    @NotNull(message = "User cannot be null")
    private User user;

    /**
     * The assessment for which the badge was awarded.
     */
    @OneToOne
    @NotNull(message = "Assessment cannot be null")
    private Assessment assessment;
}
