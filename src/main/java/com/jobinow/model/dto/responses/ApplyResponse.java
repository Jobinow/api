package com.jobinow.model.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jobinow.model.entities.Apply;
import com.jobinow.model.enums.ApplyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

/**
 * DTO Response for {@link Apply}
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplyResponse extends AbstractResponse {
    UserResponses jobSeeker;

    OfferResponse offer;

    Set<AttachmentResponse> resumePdfs;
    ApplyStatus status;
}