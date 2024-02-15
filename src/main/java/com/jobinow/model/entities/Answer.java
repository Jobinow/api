package com.jobinow.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

/**
 * Entity representing a user's answer to a question in a quiz.
 * It includes references to the question being answered, the user providing the answer,
 * and a set of selected options that constitute the answer.
 */
@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Answer extends AbstractEntity {

    /**
     * The question to which this answer is related.
     * This association is mandatory, ensuring that each answer is linked to a specific question.
     */
    @NotNull(message = "Question cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    /**
     * A set of options selected by the user as their answer.
     * This collection contains the choices made by the user in response to the question.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "assessment cannot be null")
    @JoinColumn(name = "assessment_id", nullable = false)
    private Assessment assessment;

    /**
     * The user who provided this answer.
     * This field is mandatory and links the answer to the user who answered the question.
     */
    @NotNull(message = "User cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}