package com.jobinow.model.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jobinow.model.entities.Option;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Data Transfer Object (DTO) for sending response data related to an {@link Option}.
 * This DTO encapsulates the answer data sent back to clients in response to their requests.
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OptionResponse extends AbstractResponse {
    @NotNull(message = "Answer content cannot be null")
    @Size(message = "Answer content must be between 1 and 500 characters", min = 1, max = 500)
    String content;
}