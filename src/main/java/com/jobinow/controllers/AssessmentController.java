package com.jobinow.controllers;

import com.jobinow.model.dto.requests.AssessmentRequestDto;
import com.jobinow.model.dto.responses.AssessmentResponseDto;
import com.jobinow.services.spec.AssessmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Controller class for handling assessment-related operations.
 *
 * <p>This class provides RESTful endpoints for managing assessments in the quiz application.
 * It extends from the generic _Controller class, using the AssessmentService for processing
 * business logic. The controller handles CRUD operations for Assessment entities.</p>
 *
 * <p>Endpoints include creating a new assessment, updating an existing one, retrieving assessments
 * by their unique identifiers, and deleting assessments.</p>
 *
 * @version 1.0
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/assessment")
public class AssessmentController extends _Controller<UUID, AssessmentRequestDto, AssessmentResponseDto, AssessmentService> {
}
