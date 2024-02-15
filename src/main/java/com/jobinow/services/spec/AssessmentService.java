package com.jobinow.services.spec;

import com.jobinow.model.dto.requests.AssessmentRequestDto;
import com.jobinow.model.dto.responses.AssessmentResponseDto;

import java.util.UUID;

/**
 * Service interface for managing assessments in the quiz application.
 *
 * <p>This interface extends the generic _Service interface, specifying the Assessment entity.
 * It defines the standard CRUD operations (Create, Read, Update, Delete) for Assessment objects.
 * The AssessmentService is primarily used to handle business logic related to user assessments.</p>
 *
 * <p>It includes methods for creating new assessments, updating existing ones, retrieving assessments
 * by their unique identifiers, and deleting assessments.</p>
 *
 * @version 1.0
 */
public interface AssessmentService extends _Service<UUID, AssessmentRequestDto, AssessmentResponseDto> {
}
