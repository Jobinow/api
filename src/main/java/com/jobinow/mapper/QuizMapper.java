package com.jobinow.mapper;

import com.jobinow.model.dto.requests.QuizRequestDto;
import com.jobinow.model.dto.responses.QuizResponseDto;
import com.jobinow.model.entities.Quiz;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

/**
 * MapStruct mapper interface for {@link Quiz} entity.
 * This mapper provides functionality for converting between the {@link Quiz} entity
 * and its corresponding DTOs - {@link QuizRequestDto} and {@link QuizResponseDto}.
 * It uses {@link QuestionMapper} for any necessary question-related mappings.
 * <p>
 * The interface leverages MapStruct's capabilities to generate efficient and type-safe
 * mapping implementations at compile time, ensuring rapid and accurate data transformation.
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface QuizMapper extends _Mapper<UUID, QuizRequestDto, QuizResponseDto, Quiz> {
}