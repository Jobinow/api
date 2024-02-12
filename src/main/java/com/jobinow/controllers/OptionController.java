package com.jobinow.controllers;

import com.jobinow.model.dto.requests.OptionRequestDto;
import com.jobinow.model.dto.responses.OptionResponseDto;
import com.jobinow.services.spec.OptionService;
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
@RequestMapping("api/v2/option")
public class OptionController extends _Controller<UUID, OptionRequestDto, OptionResponseDto, OptionService> {
}
