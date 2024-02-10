package com.jobinow.model.entities.quiz;

import com.jobinow.model.entities.AbstractEntity;
import com.jobinow.model.entities.Profil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
    @Column(nullable = false)
    private String name;

    /**
     * Description of the badge.
     */
    @Column(length = 500)
    private String description;

    /**
     * The quiz associated with this badge.
     */
    @OneToOne
    private Quiz quiz;

    /**
     * Profiles associated with this badge.
     */
    @ManyToMany
    private Set<Profil> profile;

    /**
     * The URL of the badge's image.
     */
    private String imageUrl;
}