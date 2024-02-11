package com.jobinow.controllers;

import com.jobinow.model.dto.requests.QuizRequestDto;
import com.jobinow.model.dto.responses.QuizResponseDto;
import com.jobinow.services.spec.QuizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * REST controller for managing quizzes.
 * This class extends the generic _Controller and is responsible for handling
 * web requests related to Quiz operations. It uses the QuizService for the
 * business logic and interacts with clients through QuizRequestDto and QuizResponseDto.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">Ouharri Outman</a>
 */
@Slf4j
@Validated
@RestController
@RequestMapping("api/v2/quiz")
public class QuizController extends _Controller<UUID, QuizRequestDto, QuizResponseDto, QuizService> {
}
