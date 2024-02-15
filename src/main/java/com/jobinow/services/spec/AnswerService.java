package com.jobinow.services.spec;

import com.jobinow.model.dto.requests.AnswerRequestDto;
import com.jobinow.model.dto.responses.AnswerResponsesDto;
import com.jobinow.model.entities.Answer;
import com.jobinow.model.entities.Assessment;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing operations related to the {@link com.jobinow.model.entities.Answer} entity.
 * This interface extends the generic _Service interface, specifying AnswerRequestDto and AnswerResponsesDto
 * for CRUD operations specifically tailored to the Answer entity.
 * <p>
 * It provides an abstraction layer above the underlying repository and enables more complex business logic
 * and interactions required for handling Answer entities.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
public interface AnswerService extends _Service<UUID, AnswerRequestDto, AnswerResponsesDto> {
    List<Answer> findByAssessment(Assessment assessment);
}