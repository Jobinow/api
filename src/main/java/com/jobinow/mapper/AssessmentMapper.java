package com.jobinow.mapper;

import com.jobinow.model.dto.requests.AssessmentRequestDto;
import com.jobinow.model.dto.responses.AssessmentResponseDto;
import com.jobinow.model.entities.Assessment;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

/**
 * MapStruct mapper interface for {@link Assessment} entity.
 * This mapper provides functionality for converting between the {@link Assessment} entity
 * and its corresponding DTOs - {@link AssessmentRequestDto} and {@link AssessmentResponseDto}.
 * It uses {@link QuizMapper} and {@link UserMapper} for related quiz and user mappings.
 * <p>
 * The interface leverages MapStruct's capabilities to generate mapping implementations
 * at compile time, providing fast and type-safe mapping code.
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface AssessmentMapper extends _Mapper<UUID, AssessmentRequestDto, AssessmentResponseDto, Assessment> {
    // MapStruct will automatically implement the inherited methods, enabling efficient
    // conversions between Assessment entities and their corresponding DTOs.
}
