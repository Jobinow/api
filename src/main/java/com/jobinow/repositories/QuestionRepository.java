package com.jobinow.repositories;

import com.jobinow.model.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Spring Data JPA repository for {@link Question} entities.
 * Provides necessary CRUD operations and query methods for Question data management.
 */
public interface QuestionRepository extends JpaRepository<Question, UUID> {
}