package com.jobinow.model.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jobinow.model.entities.Subscription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * DTO for representing a response related to {@link Subscription}.
 * This DTO includes information such as the Subscription pack, jobSeeker, and common response fields.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionResponse extends AbstractResponse {
    PackResponse pack;
    UserResponses recruiter;
}