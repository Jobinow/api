package com.jobinow.mapper;

import com.jobinow.model.dto.requests.CorrectAnswerRequestDto;
import com.jobinow.model.dto.responses.CorrectAnswerResponseDto;
import com.jobinow.model.entities.CorrectAnswer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

/**
 * MapStruct mapper interface for {@link CorrectAnswer} entity.
 * This mapper provides functionality for converting between the {@link CorrectAnswer} entity
 * and its corresponding DTOs - {@link CorrectAnswerRequestDto} and {@link CorrectAnswerResponseDto}.
 * It uses {@link AnswerMapper} and {@link QuestionMapper} for related answer and question mappings.
 * <p>
 * This interface leverages MapStruct's capabilities to generate efficient and type-safe
 * mapping implementations at compile time, ensuring rapid and accurate data transformation.
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {AnswerMapper.class, QuestionMapper.class}
)
public interface CorrectAnswerMapper extends _Mapper<UUID, CorrectAnswerRequestDto, CorrectAnswerResponseDto, CorrectAnswer> {
    // MapStruct will
    // automatically implement methods for conversion between CorrectAnswer entities and DTOs.
}