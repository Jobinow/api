package com.jobinow.model.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jobinow.model.entities.Badge;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;

import java.util.List;

/**
 * Data Transfer Object (DTO) for sending response data related to a {@link Badge}.
 * This DTO encapsulates the badge data sent back to clients in response to their requests.
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BadgeResponseDto extends AbstractResponse {
    @NotNull(message = "Badge name cannot be null")
    @Size(message = "Badge name must be between 1 and 255 characters", min = 1, max = 255)
    String name;

    @Size(message = "Badge description cannot exceed 500 characters", max = 500)
    String description;

    @NotNull(message = "Badge Quiz cannot be null")
    QuizResponseDto quiz;

    List<ProfilResponse> profile;

    @URL(message = "Image URL must be valid")
    String imageUrl;
}