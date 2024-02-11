package com.jobinow.repositories;

import com.jobinow.model.entities.CorrectAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Spring Data JPA repository for {@link CorrectAnswer} entities.
 * Enables CRUD operations and queries for handling CorrectAnswer data.
 */
public interface CorrectAnswerRepository extends JpaRepository<CorrectAnswer, UUID> {
}