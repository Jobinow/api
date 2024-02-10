package com.jobinow.model.entities.quiz;

import com.jobinow.model.entities.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


/**
 * Represents a correct answer for a quiz question.
 * Links a specific answer to a question.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CorrectAnswer extends AbstractEntity {

    /**
     * The correct answer for the question.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answer;

    /**
     * The question associated with this answer.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
}
