package com.jobinow.mapper;

import com.jobinow.model.dto.requests.SubscriptionRequest;
import com.jobinow.model.dto.responses.SubscriptionResponse;
import com.jobinow.model.entities.Subscription;
import org.mapstruct.*;

import java.util.UUID;

/**
 * Mapper interface for converting between {@link SubscriptionRequest}, {@link SubscriptionResponse}, and {@link Subscription} entities.
 * Extends the generic {@link _Mapper} interface with UUID as the identifier type.
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface SubscriptionMapper extends _Mapper<UUID, SubscriptionRequest, SubscriptionResponse, Subscription> {
    @Override
    @Mapping(target = "recruiter", source = "recruiter")
    Subscription toEntityFromRequest(SubscriptionRequest request);

    @Override
    @Mapping(target = "recruiter", source = "recruiter")
    Subscription toEntityFromResponse(SubscriptionResponse response);

    @Override
    @Mapping(target = "recruiter", source = "recruiter")
    SubscriptionResponse toResponse(Subscription entity);
}