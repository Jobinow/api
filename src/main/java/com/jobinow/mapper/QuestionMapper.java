package com.jobinow.mapper;

import com.jobinow.model.dto.requests.QuestionRequestDto;
import com.jobinow.model.dto.responses.QuestionResponseDto;
import com.jobinow.model.entities.Question;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

/**
 * MapStruct mapper interface for {@link Question} entity.
 * This mapper provides functionality for converting between the {@link Question} entity
 * and its corresponding DTOs - {@link QuestionRequestDto} and {@link QuestionResponseDto}.
 * The mapper uses {@link QuizMapper} for any necessary quiz-related mappings.
 * <p>
 * MapStruct generates the implementation at compile-time, providing fast, type-safe,
 * and maintainable code for entity-DTO transformation.
 */
@Mapper(

        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface QuestionMapper extends _Mapper<UUID, QuestionRequestDto, QuestionResponseDto, Question> {
    // Inherited methods from _Mapper will automatically be implemented by MapStruct,
    // enabling conversions between Question entities and DTOs.
}
