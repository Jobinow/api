package com.jobinow.model.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jobinow.model.entities.Question;
import com.jobinow.model.enums.QuestionType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;

import java.util.Set;

/**
 * Data Transfer Object (DTO) for sending response data related to a {@link Question}.
 * This DTO encapsulates the question data sent back to clients in response to their requests.
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionResponseDto extends AbstractResponse {
    @NotNull(message = "Question text cannot be null")
    @Size(message = "Question text must be between 1 and 1000 characters", min = 1, max = 1000)
    String text;

    @URL(message = "Image URL must be valid")
    String imageUrl;

    @NotNull(message = "Question type cannot be null")
    QuestionType type;

    @Min(message = "Time must be greater than or equal to 5 seconds", value = 5)
    @Positive(message = "Time must be a positive number")
    int time;

    Set<OptionResponse> options;
}