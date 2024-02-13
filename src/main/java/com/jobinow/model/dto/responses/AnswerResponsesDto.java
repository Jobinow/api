package com.jobinow.model.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jobinow.model.entities.Answer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

/**
 * Response DTO for {@link Answer} entity.
 * This DTO encapsulates the data sent back to clients in response to requests concerning an Answer.
 * It includes details of the question, the options provided, and the user associated with the answer.
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswerResponsesDto extends AbstractResponse {
    @NotNull(message = "Question cannot be null")
    QuestionResponseDto question;

    Set<OptionResponse> options;

    @NotNull(message = "User cannot be null")
    UserResponses user;
}