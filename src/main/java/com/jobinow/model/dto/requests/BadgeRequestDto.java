package com.jobinow.model.dto.requests;

import com.jobinow.model.dto.responses.ProfilResponse;
import com.jobinow.model.dto.responses.QuizResponseDto;
import com.jobinow.model.entities.Badge;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.Set;

/**
 * Data Transfer Object (DTO) for creating or updating a {@link Badge}.
 * This DTO encapsulates the data required to create or modify a badge
 * in the context of a quiz application.
 */
public record BadgeRequestDto(
        @NotNull(message = "Badge name cannot be null")
        @Size(message = "Badge name must be between 1 and 255 characters", min = 1, max = 255)
        String name,

        @Size(message = "Badge description cannot exceed 500 characters", max = 500)
        String description,

        @NotNull(message = "Badge Quiz cannot be null")
        QuizResponseDto quiz,

        Set<ProfilResponse> profile,

        @URL(message = "Image URL must be valid")
        String imageUrl
) implements _Request {
}