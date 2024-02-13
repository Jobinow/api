package com.jobinow.controllers;

import com.jobinow.model.dto.requests.AnswerRequestDto;
import com.jobinow.model.dto.responses.AnswerResponsesDto;
import com.jobinow.services.spec.AnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Controller class for managing answer-related operations.
 *
 * <p>This class provides RESTful endpoints to handle operations related to answers in the system.
 * It extends a generic controller class which implements standard CRUD operations. These operations
 * use {@link AnswerService} for business logic and handle requests and responses as {@link AnswerRequestDto}
 * and {@link AnswerResponsesDto}, respectively.</p>
 *
 * @version 1.0
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/answer")
public class AnswerController extends _Controller<UUID, AnswerRequestDto, AnswerResponsesDto, AnswerService> {
}
