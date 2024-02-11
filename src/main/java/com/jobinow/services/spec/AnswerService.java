package com.jobinow.services.spec;

import com.jobinow.model.dto.requests.AnswerRequestDto;
import com.jobinow.model.dto.responses.AnswerResponseDto;
import com.jobinow.model.entities.Answer;

import java.util.UUID;

/**
 * Service interface for managing operations related to the {@link Answer} entity.
 * This interface extends the generic _Service interface, specifying AnswerRequestDto and AnswerResponseDto
 * for CRUD operations specifically tailored to the Answer entity.
 * <p>
 * It provides an abstraction layer above the underlying repository and allows for more complex business logic
 * and interactions that might be required for handling Answer entities.
 */
public interface AnswerService extends _Service<UUID, AnswerRequestDto, AnswerResponseDto> {
}
