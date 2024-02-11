package com.jobinow.services.spec;

import com.jobinow.model.dto.requests.QuizRequestDto;
import com.jobinow.model.dto.responses.QuizResponseDto;

import java.util.UUID;

/**
 * Service interface for managing quiz-related operations.
 * This interface extends the generic _Service interface, specifying QuizRequestDto and QuizResponseDto
 * for CRUD operations related to Quiz entities.
 */
public interface QuizService extends _Service<UUID, QuizRequestDto, QuizResponseDto> {
}
