package com.jobinow.model.dto.requests;

import com.jobinow.model.dto.responses.AnswerResponseDto;
import com.jobinow.model.dto.responses.QuestionResponseDto;
import com.jobinow.model.entities.CorrectAnswer;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) for creating or updating a {@link CorrectAnswer}.
 * This DTO encapsulates the data required to create or modify a correct answer
 * in the context of a quiz application.
 */
public record CorrectAnswerRequestDto(
        @NotNull(message = "Answer cannot be null")
        AnswerResponseDto answer,

        @NotNull(message = "Question cannot be null")
        QuestionResponseDto question
) implements _Request {
}