package com.jobinow.mapper;

import com.jobinow.model.dto.requests.AnswerRequestDto;
import com.jobinow.model.dto.responses.AnswerResponseDto;
import com.jobinow.model.entities.Answer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

/**
 * MapStruct mapper interface for {@link Answer} entity.
 * This mapper provides functionality for converting between {@link Answer} entity
 * and {@link AnswerRequestDto} and {@link AnswerResponseDto}.
 * It leverages {@link QuestionMapper} for question-related mappings.
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface AnswerMapper extends _Mapper<UUID, AnswerRequestDto, AnswerResponseDto, Answer> {
    // The methods inherited from _Mapper will be automatically implemented by MapStruct,
    // enabling conversion between Answer entities and DTOs.
}