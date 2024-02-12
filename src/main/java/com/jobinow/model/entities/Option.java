package com.jobinow.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Represents an option (Answer) for a question in a quiz.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Option extends AbstractEntity {

    /**
     * The text of the option.
     */
    @NotNull(message = "Answer content cannot be null")
    @Size(min = 1, max = 500, message = "Answer content must be between 1 and 500 characters")
    @Column(nullable = false, length = 500)
    private String content;

    /**
     * The question to which this option belongs.
     */
    @NotNull(message = "Question cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    /**
     * A flag indicating whether this is the correct answer for the question.
     */
    @NotNull(message = "Correctness flag cannot be null")
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isCorrect = false;
}
