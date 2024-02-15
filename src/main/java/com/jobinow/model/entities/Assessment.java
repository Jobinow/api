package com.jobinow.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

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
    @NotNull(message = "Quiz cannot be null")
    private Quiz quiz;

    /**
     * The user who took this assessment.
     */
    @ManyToOne
    @NotNull(message = "User cannot be null")
    private User user;

    /**
     * The timestamp when the assessment was started.
     */
    @CreationTimestamp
    @NotNull(message = "Start time cannot be null")
    private LocalDateTime startedAt;

    /**
     * The timestamp when the assessment was finished.
     */
    private LocalDateTime finishedAt;

    /**
     * The percentage result of the assessment.
     */
    @Min(value = 0, message = "Percentage result cannot be negative")
    private double percentageResult;

    /**
     * A flag indicating whether the assessment was successfully completed.
     */
    private boolean succeeded;
}