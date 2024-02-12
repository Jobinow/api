package com.jobinow.services.impl;

import com.jobinow.mapper.OptionMapper;
import com.jobinow.model.dto.requests.OptionRequestDto;
import com.jobinow.model.dto.responses.OptionResponseDto;
import com.jobinow.model.entities.Option;
import com.jobinow.repositories.OptionRepository;
import com.jobinow.services.spec.OptionService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Implementation of the AnswerService interface.
 * This class provides concrete implementations of CRUD operations for {@link Option} entities.
 * It extends the generic _ServiceImp, utilizing AnswerRepository for data access and AnswerMapper
 * for mapping between entities and DTOs.
 * <p>
 * The service is annotated with @CacheConfig to leverage caching capabilities for improved performance.
 */
@Service
@AllArgsConstructor
@CacheConfig(cacheNames = "Answer")
public class OptionServiceImpl extends _ServiceImp<UUID, OptionRequestDto, OptionResponseDto, Option, OptionRepository, OptionMapper> implements OptionService {
}
