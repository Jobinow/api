package com.jobinow.services.impl;

import com.jobinow.mapper.AnswerMapper;
import com.jobinow.model.dto.requests.AnswerRequestDto;
import com.jobinow.model.dto.responses.AnswerResponse;
import com.jobinow.model.dto.responses.AnswerResponseDto;
import com.jobinow.model.entities.Answer;
import com.jobinow.repositories.AnswerRepository;
import com.jobinow.services.spec.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Implementation of the AnswerService interface.
 * This class provides concrete implementations of CRUD operations for {@link Answer} entities.
 * It extends the generic _ServiceImp, utilizing AnswerRepository for data access and AnswerMapper
 * for mapping between entities and DTOs.
 * <p>
 * The service is annotated with @CacheConfig to leverage caching capabilities for improved performance.
 */
@Service
@AllArgsConstructor
@CacheConfig(cacheNames = "Answer")
public class AnswerServiceImpl extends _ServiceImp<UUID, AnswerRequestDto, AnswerResponseDto, Answer, AnswerRepository, AnswerMapper> implements AnswerService {
}
