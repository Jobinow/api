package com.jobinow.services.impl;

import com.jobinow.mapper.QuestionMapper;
import com.jobinow.model.dto.requests.QuestionRequestDto;
import com.jobinow.model.dto.responses.QuestionResponseDto;
import com.jobinow.model.entities.Question;
import com.jobinow.repositories.QuestionRepository;
import com.jobinow.services.spec.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Implementation of the QuestionService interface.
 * This class provides concrete implementations of CRUD operations for {@link Question} entities.
 * It extends the generic _ServiceImp, utilizing QuestionRepository for data access and QuestionMapper
 * for mapping between entities and DTOs.
 *
 * The service is annotated with @CacheConfig to leverage caching capabilities for improved performance.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "Question")
public class QuestionServiceImpl extends _ServiceImp<UUID, QuestionRequestDto, QuestionResponseDto, Question, QuestionRepository, QuestionMapper> implements QuestionService {
}
