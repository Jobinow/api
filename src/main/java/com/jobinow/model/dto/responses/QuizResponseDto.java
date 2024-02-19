package com.jobinow.model.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jobinow.model.entities.Quiz;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
 * Data Transfer Object (DTO) for sending response data related to a {@link Quiz}.
 * This DTO encapsulates the quiz data sent back to clients in response to their requests.
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuizResponseDto extends AbstractResponse {
    @NotNull(message = "Quiz title cannot be null")
    @Size(message = "Quiz title must be between 1 and 255 characters", min = 1, max = 255)
    String title;

    @Size(message = "Quiz description cannot exceed 500 characters", max = 500)
    String description;

    @URL(message = "Image URL must be valid")
    String imageUrl;

    @Min(message = "Passing percentage cannot be less than 0", value = 0)
    @Max(message = "Passing percentage cannot be more than 100", value = 100)
    int passingPercentage;

    List<QuestionResponseDto> questions;
}