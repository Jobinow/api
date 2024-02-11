package com.jobinow.controllers;

import com.jobinow.model.dto.requests.AnswerRequestDto;
import com.jobinow.model.dto.responses.AnswerResponseDto;
import com.jobinow.services.spec.AnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * REST controller for managing answers associated with questions.
 * This controller extends the generic _Controller class and is responsible for handling
 * web requests related to Answer operations. It uses the AnswerService for business logic
 * and interacts with clients through AnswerRequestDto and AnswerResponseDto.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">Ouharri Outman</a>
 */
@Slf4j
@Validated
@RestController
@RequestMapping("api/v2/answer")
public class AnswerController extends _Controller<UUID, AnswerRequestDto, AnswerResponseDto, AnswerService> {
}
