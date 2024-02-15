package com.jobinow.services.impl;

import com.google.common.util.concurrent.AtomicDouble;
import com.jobinow.mapper.AssessmentMapper;
import com.jobinow.model.dto.requests.AssessmentRequestDto;
import com.jobinow.model.dto.responses.AssessmentResponseDto;
import com.jobinow.model.entities.Answer;
import com.jobinow.model.entities.Assessment;
import com.jobinow.model.entities.Question;
import com.jobinow.repositories.AssessmentRepository;
import com.jobinow.services.spec.AnswerService;
import com.jobinow.services.spec.AssessmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Service implementation for handling assessment-related operations.
 * This class extends the generic _ServiceImp and provides a concrete implementation for assessment management,
 * particularly focusing on the scoring logic of different types of quiz questions.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "assessment")
public class AssessmentServiceImpl extends _ServiceImp<UUID, AssessmentRequestDto, AssessmentResponseDto, Assessment, AssessmentRepository, AssessmentMapper> implements AssessmentService {
    private final AnswerService answerService;


    /**
     * Calculates the score for a given assessment based on the answers provided.
     * The calculation varies depending on the type of questions in the quiz.
     *
     * @param request The assessment data for which the score is to be calculated.
     * @return An Optional containing the updated AssessmentResponseDto with the calculated score.
     */
    Optional<AssessmentResponseDto> calculateScore(AssessmentResponseDto request) {
        Assessment assessment = mapper.toEntityFromResponse(request);
        List<Answer> answer = answerService.findByAssessment(assessment);
        int numberOfQuestions = assessment.getQuiz().getQuestions().size();
        AtomicDouble answerPercent = new AtomicDouble();

        answer.forEach(a -> {
            Question question = a.getQuestion();
            switch (question.getType()) {
                case MULTIPLE_CHOICE:
                    answerPercent.addAndGet(calculateMultipleChoiceScore(a));
                    break;
                case SINGLE_CHOICE:
                    answerPercent.addAndGet(calculateSingleChoiceScore(a));
                case TRUE_FALSE:
                    answerPercent.addAndGet(calculateTrueFalseScore(a));
                    break;
                case SHORT_ANSWER:
                    answerPercent.addAndGet(calculateShortAnswerScore(a));
                    break;
                case EASY:
                    answerPercent.addAndGet(calculateEasyScore(a));
                    break;
                default:
                    log.error("Unknown question type: {}", question.getType());
            }
        });

        double score = numberOfQuestions * 100 / answerPercent.get();

        assessment.setPercentageResult(score);

        return Optional.ofNullable(
                mapper.toResponse(
                        repository.save(assessment)
                )
        );
    }

    private int calculateSingleChoiceScore(Answer a) {
        if (a.getOptions().size() > 1)
            return 0;

        return a.getOptions().get(0).isCorrect() ? 1 : 0;
    }

    private double calculateEasyScore(Answer a) {
        AtomicInteger answerScore = new AtomicInteger();
        AtomicInteger correctAnswer = new AtomicInteger();
        a.getOptions().forEach(o -> {
            if (o.isCorrect()) {
                answerScore.getAndIncrement();
            }
        });
        a.getQuestion().getOptions().forEach(o -> {
            if (o.isCorrect()) {
                correctAnswer.getAndIncrement();
            }
        });
        return (answerScore.doubleValue() / correctAnswer.doubleValue()) / 2;
    }

    private int calculateShortAnswerScore(Answer a) {
        if (a.getOptions().size() > 1)
            return 0;

        if (
                a.getOptions().get(0).getContent()
                        .equalsIgnoreCase(a.getQuestion().getOptions().get(0).getContent())
        )
            return 1;

        return 0;
    }

    private int calculateTrueFalseScore(Answer a) {
        if (a.getOptions().size() > 1)
            return 0;

        return a.getOptions().get(0).isCorrect() ? 1 : 0;
    }

    private double calculateMultipleChoiceScore(Answer a) {
        AtomicInteger answerScore = new AtomicInteger();
        AtomicInteger correctAnswer = new AtomicInteger();
        a.getOptions().forEach(o -> {
            if (o.isCorrect()) {
                answerScore.getAndIncrement();
            }
        });
        a.getQuestion().getOptions().forEach(o -> {
            if (o.isCorrect()) {
                correctAnswer.getAndIncrement();
            }
        });
        return answerScore.doubleValue() / correctAnswer.doubleValue();
    }
}
