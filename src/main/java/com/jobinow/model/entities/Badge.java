package com.jobinow.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;

import java.util.Set;

/**
 * Represents a badge awarded to a user for achieving a certain score or milestone in a quiz.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Badge extends AbstractEntity {

    /**
     * The name of the badge.
     */
    @NotNull(message = "Badge name cannot be null")
    @Size(min = 1, max = 255, message = "Badge name must be between 1 and 255 characters")
    @Column(nullable = false)
    private String name;

    /**
     * Description of the badge.
     */
    @Size(max = 500, message = "Badge description cannot exceed 500 characters")
    @Column(length = 500)
    private String description;

    /**
     * The quiz associated with this badge.
     */
    @OneToOne
    @NotNull(message = "Badge Quiz cannot be null")
    private Quiz quiz;

    /**
     * Profiles associated with this badge.
     */
    @ManyToMany
    private Set<Profil> profile;

    /**
     * The URL of the badge's image.
     */
    @URL(message = "Image URL must be valid")
    private String imageUrl;
}
