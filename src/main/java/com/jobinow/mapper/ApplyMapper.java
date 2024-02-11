package com.jobinow.mapper;

import com.jobinow.model.dto.requests.ApplyRequest;
import com.jobinow.model.dto.responses.ApplyResponse;
import com.jobinow.model.entities.Apply;
import org.mapstruct.*;

import java.util.UUID;

/**
 * Mapper interface for mapping {@link Apply} entities to and from DTOs.
 *
 * @version 1.0
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ApplyMapper extends _Mapper<UUID, ApplyRequest, ApplyResponse, Apply> {

    /**
     * Maps an {@link ApplyRequest} to an {@link Apply} entity.
     *
     * @param request The ApplyRequest DTO.
     * @return The mapped Apply entity.
     */
    @Override
    @Mapping(target = "resumePdfs", source = "attachmentUrls")
    Apply toEntityFromRequest(ApplyRequest request);

    /**
     * Maps an {@link ApplyResponse} to an {@link Apply} entity.
     *
     * @param response The ApplyResponse DTO.
     * @return The mapped Apply entity.
     */
    @Override
    @Mapping(target = "resumePdfs", source = "resumePdfs")
    Apply toEntityFromResponse(ApplyResponse response);

    /**
     * Maps an {@link Apply} entity to an {@link ApplyResponse}.
     *
     * @param entity The Apply entity.
     * @return The mapped ApplyResponse DTO.
     */
    @Override
    @Mapping(target = "resumePdfs", source = "resumePdfs")
    ApplyResponse toResponse(Apply entity);
}