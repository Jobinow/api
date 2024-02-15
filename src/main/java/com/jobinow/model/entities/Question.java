package com.jobinow.model.entities;

import com.jobinow.model.enums.QuestionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;

import java.util.List;

/**
 * Represents a question in a quiz.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Question extends AbstractEntity {

    /**
     * The text of the question.
     */
    @NotNull(message = "Question text cannot be null")
    @Size(min = 1, max = 1000, message = "Question text must be between 1 and 1000 characters")
    @Column(nullable = false, length = 1000)
    private String text;

    /**
     * The URL of the question's image.
     */
    @URL(message = "Image URL must be valid")
    private String imageUrl;

    /**
     * The type of the question.
     */
    @NotNull(message = "Question type cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType type;

    /**
     * The Time (in second) allocated to answer the question.
     */
    @Positive(message = "Time must be a positive number")
    @Min(value = 5, message = "Time must be greater than or equal to 5 seconds")
    private int time;

    /**
     * The quiz to which this question belongs.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Quiz cannot be null")
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    /**
     * A set of options for this question.
     */
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options;
}
