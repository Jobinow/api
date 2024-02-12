package com.jobinow.mapper;

import com.jobinow.model.dto.requests.AnswerRequestDto;
import com.jobinow.model.dto.responses.AnswerResponsesDto;
import com.jobinow.model.entities.Answer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

/**
 * MapStruct mapper interface for {@link Answer} entity.
 * This mapper provides functionality for converting between the {@link Answer} entity
 * and its corresponding DTOs - {@link AnswerRequestDto} and {@link AnswerResponsesDto}.
 * It leverages other mappers like {@link QuestionMapper} and {@link OptionMapper} for related mappings.
 * <p>
 * The interface is annotated to indicate the usage of constructor-based injection for Spring,
 * and to ignore unmapped target properties.
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {QuestionMapper.class, OptionMapper.class}
)
public interface AnswerMapper extends _Mapper<UUID, AnswerRequestDto, AnswerResponsesDto, Answer> {
}