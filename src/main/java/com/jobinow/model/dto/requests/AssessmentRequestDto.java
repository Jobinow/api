package com.jobinow.model.dto.requests;

import com.jobinow.model.dto.responses.QuizResponseDto;
import com.jobinow.model.dto.responses.UserResponses;
import com.jobinow.model.entities.Assessment;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for creating or updating an {@link Assessment}.
 * This DTO encapsulates the data required to create or modify an assessment
 * in the context of a quiz application.
 */
public record AssessmentRequestDto(
        @NotNull(message = "Quiz cannot be null") QuizResponseDto quiz,

        @NotNull(message = "User cannot be null")
        UserResponses user,

        @NotNull(message = "Start time cannot be null")
        LocalDateTime startedAt,

        LocalDateTime finishedAt
) implements _Request {
}