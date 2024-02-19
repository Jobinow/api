package com.jobinow.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;

import java.util.List;

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
    @NotNull(message = "Quiz title cannot be null")
    @Size(min = 1, max = 255, message = "Quiz title must be between 1 and 255 characters")
    @Column(nullable = false)
    private String title;

    /**
     * A description of the quiz.
     */
    @Size(max = 500, message = "Quiz description cannot exceed 500 characters")
    @Column(length = 500)
    private String description;

    /**
     * The URL of the quiz's image.
     */
    @URL(message = "Image URL must be valid")
    private String imageUrl;

    /**
     * The percentage of correct questions required to pass the quiz.
     */
    @Min(value = 0, message = "Passing percentage cannot be less than 0")
    @Max(value = 100, message = "Passing percentage cannot be more than 100")
    private int passingPercentage;

    /**
     * The badge associated with this quiz.
     */
    @OneToOne(mappedBy = "quiz")
    private Badge badge;

    /**
     * A set of questions in this quiz.
     */
    @OneToMany(
            mappedBy = "quiz",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Question> questions;
}