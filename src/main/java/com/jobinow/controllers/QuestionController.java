package com.jobinow.controllers;

import com.jobinow.model.dto.requests.QuestionRequestDto;
import com.jobinow.model.dto.responses.QuestionResponseDto;
import com.jobinow.services.spec.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * REST controller for managing question-related operations.
 * This class extends the generic _Controller and handles web requests related to
 * questions in quizzes. It uses the QuestionService for business logic and interacts
 * with clients through QuestionRequestDto and QuestionResponseDto.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">Ouharri Outman</a>
 */
@Slf4j
@Validated
@RestController
@RequestMapping("api/v2/question")
public class QuestionController extends _Controller<UUID, QuestionRequestDto, QuestionResponseDto, QuestionService> {
}
