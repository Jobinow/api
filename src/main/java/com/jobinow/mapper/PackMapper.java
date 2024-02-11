package com.jobinow.mapper;

import com.jobinow.model.dto.requests.PackRequest;
import com.jobinow.model.dto.responses.PackResponse;
import com.jobinow.model.entities.Pack;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

/**
 * Mapper interface for converting between {@link PackRequest}, {@link PackResponse}, and {@link Pack} entities.
 * Extends the generic {@link _Mapper} interface with UUID as the identifier type.
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface PackMapper extends _Mapper<UUID, PackRequest, PackResponse, Pack> {
}