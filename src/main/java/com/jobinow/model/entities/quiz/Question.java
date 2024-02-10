package com.jobinow.model.entities.quiz;

import com.jobinow.model.entities.AbstractEntity;
import com.jobinow.model.enums.QuestionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

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
    @Column(nullable = false, length = 1000)
    private String text;

    /**
     * The URL of the question's image.
     */
    private String imageUrl;

    /**
     * The type of the question.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType type;

    /**
     * The Time (in second) allocated to answer the question.
     */
    private int timed;

    /**
     * The quiz to which this question belongs.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    /**
     * A set of correct answers for this question.
     */
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CorrectAnswer> correctAnswers;

    /**
     * A set of options for this question.
     */
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Answer> options;
}
