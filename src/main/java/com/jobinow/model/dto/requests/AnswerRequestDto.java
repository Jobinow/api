package com.jobinow.model.dto.requests;

import com.jobinow.model.dto.responses.OptionResponseDto;
import com.jobinow.model.entities.Answer;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

/**
 * Request DTO for {@link Answer} entity.
 * This DTO is used to encapsulate the data necessary for creating or updating an Answer.
 * It includes the associated question and a set of selected options.
 */
public record AnswerRequestDto(
        @NotNull(message = "Question cannot be null")
        QuestionRequestDto question,

        @NotEmpty(message = "Options cannot be empty")
        Set<OptionResponseDto> options
) implements _Request {
}