package com.jobinow.model.dto.requests;

import com.jobinow.model.dto.responses.QuestionResponseDto;
import com.jobinow.model.entities.Answer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for creating or updating an {@link Answer}.
 * This DTO encapsulates the data required to create or modify an answer
 * in a quiz question.
 */
public record AnswerRequestDto(

        @NotNull(message = "Answer content cannot be null")
        @Size(message = "Answer content must be between 1 and 500 characters", min = 1, max = 500)
        String content,


        @NotNull(message = "Question cannot be null")
        QuestionResponseDto question
) implements _Request {
}