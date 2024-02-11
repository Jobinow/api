package com.jobinow.repositories;

import com.jobinow.model.entities.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Spring Data JPA repository for {@link Assessment} entities.
 * Offers CRUD operations and query capabilities for Assessment data management.
 */
public interface AssessmentRepository extends JpaRepository<Assessment, UUID> {
}