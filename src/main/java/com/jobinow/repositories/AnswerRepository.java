package com.jobinow.repositories;

import com.jobinow.model.entities.Answer;
import com.jobinow.model.entities.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for {@link Answer} entities.
 * This interface provides the mechanism for CRUD operations on Answer entities, as well as
 * the ability to execute custom queries as defined.
 * <p>
 * Extends JpaRepository, leveraging Spring Data's repository abstraction to simplify the data access layer.
 */
public interface AnswerRepository extends JpaRepository<Answer, UUID> {
    List<Answer> findByAssessment(Assessment assessment);
}