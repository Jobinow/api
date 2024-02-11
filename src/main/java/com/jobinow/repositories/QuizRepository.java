package com.jobinow.repositories;

import com.jobinow.model.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Spring Data JPA repository for {@link Quiz} entities.
 * Offers CRUD functionalities and data access methods for Quiz entities.
 */
public interface QuizRepository extends JpaRepository<Quiz, UUID> {
}