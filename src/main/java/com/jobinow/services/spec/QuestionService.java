package com.jobinow.services.spec;

import com.jobinow.model.dto.requests.QuestionRequestDto;
import com.jobinow.model.dto.responses.QuestionResponseDto;
import com.jobinow.model.entities.Question;

import java.util.UUID;

/**
 * Service interface for managing operations related to the {@link Question} entity.
 * This interface extends the generic _Service interface, specifying QuestionRequestDto and QuestionResponseDto
 * for CRUD operations specifically tailored to the Question entity.
 *
 * It provides an abstraction layer above the underlying repository and allows for more complex business logic
 * and interactions that might be required for handling Question entities.
 */
public interface QuestionService extends _Service<UUID, QuestionRequestDto, QuestionResponseDto> {
}
