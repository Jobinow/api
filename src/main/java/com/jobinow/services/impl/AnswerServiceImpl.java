package com.jobinow.services.impl;

import com.jobinow.mapper.AnswerMapper;
import com.jobinow.model.dto.requests.AnswerRequestDto;
import com.jobinow.model.dto.responses.AnswerResponsesDto;
import com.jobinow.model.entities.Answer;
import com.jobinow.repositories.AnswerRepository;

import java.util.UUID;

/**
 * Implementation of the AnswerService interface.
 * This class provides concrete implementations of CRUD operations for {@link Answer} entities.
 * Extends the generic _ServiceImp, utilizing AnswerRepository for data access and AnswerMapper
 * for mapping between entities and DTOs.
 * <p>
 * This service handles the business logic associated with Answer entities and bridges the gap
 * between the controller layer and the data access layer.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
public class AnswerServiceImpl extends _ServiceImp<UUID, AnswerRequestDto, AnswerResponsesDto, Answer, AnswerRepository, AnswerMapper> {
}
