package com.jobinow.services.impl;

import com.jobinow.mapper.QuizMapper;
import com.jobinow.model.dto.requests.QuizRequestDto;
import com.jobinow.model.dto.responses.QuizResponseDto;
import com.jobinow.model.entities.Quiz;
import com.jobinow.repositories.QuizRepository;
import com.jobinow.services.spec.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service implementation for managing quizzes.
 * This class extends the generic _ServiceImp to provide CRUD operations for the Quiz entity.
 * It implements the QuizService interface and uses QuizRepository for data access and QuizMapper for DTO-entity conversion.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "Quiz")
public class QuizServiceImpl extends _ServiceImp<UUID, QuizRequestDto, QuizResponseDto, Quiz, QuizRepository, QuizMapper> implements QuizService {
}
