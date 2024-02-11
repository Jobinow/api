package com.jobinow.model.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jobinow.model.entities.Assessment;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for sending response data related to an {@link Assessment}.
 * This DTO encapsulates the assessment data sent back to clients in response to their requests.
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssessmentResponseDto extends AbstractResponse {
    @NotNull(message = "Quiz cannot be null")
    QuizResponseDto quiz;

    @NotNull(message = "User cannot be null")
    UserResponses user;

    @NotNull(message = "Start time cannot be null")
    LocalDateTime startedAt;

    LocalDateTime finishedAt;

    @Min(message = "Percentage result cannot be negative", value = 0)
    int percentageResult;

    boolean succeeded;
}