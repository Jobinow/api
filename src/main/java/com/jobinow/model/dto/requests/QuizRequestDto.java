package com.jobinow.model.dto.requests;

import com.jobinow.model.entities.Quiz;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

/**
 * Data Transfer Object (DTO) for creating or updating a {@link Quiz}.
 * This DTO encapsulates the data required to create or modify a quiz
 * in a quiz application.
 */
public record QuizRequestDto(
        @NotNull(message = "Quiz title cannot be null")
        @Size(message = "Quiz title must be between 1 and 255 characters", min = 1, max = 255)
        String title,

        @Size(message = "Quiz description cannot exceed 500 characters", max = 500)
        String description,

        @URL(message = "Image URL must be valid")
        String imageUrl,

        @Min(message = "Passing percentage cannot be less than 0", value = 0)
        @Max(message = "Passing percentage cannot be more than 100", value = 100)
        int passingPercentage
) implements _Request {
}