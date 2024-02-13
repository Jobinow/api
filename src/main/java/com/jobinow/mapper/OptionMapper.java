package com.jobinow.mapper;

import com.jobinow.model.dto.requests.OptionRequestDto;
import com.jobinow.model.dto.responses.OptionResponseDto;
import com.jobinow.model.entities.Option;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

/**
 * MapStruct mapper interface for {@link Option} entity.
 * This mapper provides functionality for converting between {@link Option} entity
 * and {@link OptionRequestDto} and {@link OptionResponseDto}.
 * It leverages {@link QuestionMapper} for question-related mappings.
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface OptionMapper extends _Mapper<UUID, OptionRequestDto, OptionResponseDto, Option> {
    // The methods inherited from _Mapper will be automatically implemented by MapStruct,
    // enabling conversion between Answer entities and DTOs.
}