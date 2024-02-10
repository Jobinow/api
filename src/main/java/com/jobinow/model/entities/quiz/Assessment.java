package com.jobinow.model.entities.quiz;

import com.jobinow.model.entities.AbstractEntity;
import com.jobinow.model.entities.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Represents an assessment taken by a user. It links a user to a specific quiz
 * and tracks the start and finish times, the percentage result, and whether the
 * assessment was successfully completed.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Assessment extends AbstractEntity {

    /**
     * The quiz associated with this assessment.
     */
    @ManyToOne
    private Quiz quiz;

    /**
     * The user who took this assessment.
     */
    @ManyToOne
    private User user;

    /**
     * The timestamp when the assessment was started.
     */
    private LocalDateTime startedAt;

    /**
     * The timestamp when the assessment was finished.
     */
    private LocalDateTime finishedAt;

    /**
     * The percentage result of the assessment.
     */
    private int percentageResult;

    /**
     * A flag indicating whether the assessment was successfully completed.
     */
    private boolean succeeded;
}
