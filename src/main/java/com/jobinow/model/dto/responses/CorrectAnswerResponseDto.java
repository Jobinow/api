package com.jobinow.model.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jobinow.model.entities.CorrectAnswer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Data Transfer Object (DTO) for sending response data related to a {@link CorrectAnswer}.
 * This DTO encapsulates the correct answer data sent back to clients in response to their requests.
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CorrectAnswerResponseDto extends AbstractResponse {
    @NotNull(message = "Answer cannot be null")
    AnswerResponseDto answer;

    @NotNull(message = "Question cannot be null")
    QuestionResponseDto question;
}