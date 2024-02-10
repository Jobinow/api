package com.jobinow.model.entities.quiz;

import com.jobinow.model.entities.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

/**
 * Represents a quiz containing multiple questions.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Quiz extends AbstractEntity {

    /**
     * The title of the quiz.
     */
    @Column(nullable = false)
    private String title;

    /**
     * A description of the quiz.
     */
    @Column(length = 500)
    private String description;

    /**
     * The URL of the quiz's image.
     */
    private String imageUrl;

    /**
     * the percentage of correct questions to pass the quiz.
     */
    private int passingPercentage;

    /**
     * The badge associated with this quiz.
     */
    @OneToOne
    private Badge badge;

    /**
     * A set of questions in this quiz.
     */
    @OneToMany(
            mappedBy = "quiz",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Question> questions;
}
