package com.jobinow.mapper;

import com.jobinow.model.dto.requests.BadgeRequestDto;
import com.jobinow.model.dto.responses.BadgeResponseDto;
import com.jobinow.model.entities.Badge;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

/**
 * MapStruct mapper interface for {@link Badge} entity.
 * This mapper provides functionality for converting between the {@link Badge} entity
 * and its corresponding DTOs - {@link BadgeRequestDto} and {@link BadgeResponseDto}.
 * It uses {@link QuizMapper} and {@link ProfilMapper} for related quiz and profile mappings.
 * <p>
 * The interface leverages MapStruct's capabilities to generate efficient and type-safe
 * mapping implementations at compile time.
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface BadgeMapper extends _Mapper<UUID, BadgeRequestDto, BadgeResponseDto, Badge> {
    // Inherited methods from _Mapper will be automatically implemented by MapStruct,
    // facilitating the conversion between Badge entities and DTOs.
}
