package com.jobinow.model.dto.requests;

import com.jobinow.model.dto.responses.QuestionResponseDto;
import com.jobinow.model.entities.Option;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for creating or updating an {@link Option}.
 * This DTO encapsulates the data required to create or modify an answer
 * in a quiz question.
 */
public record OptionRequestDto(

        @NotNull(message = "Answer content cannot be null")
        @Size(message = "Answer content must be between 1 and 500 characters", min = 1, max = 500)
        String content,


        @NotNull(message = "Question cannot be null")
        QuestionResponseDto question,

        @NotNull(message = "Correctness flag cannot be null")
        boolean isCorrect
) implements _Request {
}