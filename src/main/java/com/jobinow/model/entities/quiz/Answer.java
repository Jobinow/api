package com.jobinow.model.entities.quiz;

import com.jobinow.model.entities.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Represents an option for a multiple-choice question in a quiz.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Answer extends AbstractEntity {

    /**
     * The text of the option.
     */
    @Column(nullable = false, length = 500)
    private String content;

    /**
     * The question to which this option belongs.
     */
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "question_id",
            nullable = false
    )
    private Question question;

}
