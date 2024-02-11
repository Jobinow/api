package com.jobinow.model.dto.requests;

import com.jobinow.model.dto.responses.QuizResponseDto;
import com.jobinow.model.entities.Question;
import com.jobinow.model.enums.QuestionType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

/**
 * Data Transfer Object (DTO) for creating or updating a {@link Question}.
 * This DTO encapsulates the data required to create or modify a question
 * in a quiz application.
 */
public record QuestionRequestDto(
        @NotNull(message = "Question text cannot be null")
        @Size(message = "Question text must be between 1 and 1000 characters", min = 1, max = 1000) String text,

        @URL(message = "Image URL must be valid")
        String imageUrl,

        @NotNull(message = "Question type cannot be null")
        QuestionType type,

        @Min(message = "Time must be greater than or equal to 5 seconds", value = 5)
        @Positive(message = "Time must be a positive number") int time,

        @NotNull(message = "Quiz cannot be null")
        QuizResponseDto quiz
) implements _Request {
}