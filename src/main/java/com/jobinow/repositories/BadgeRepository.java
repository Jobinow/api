package com.jobinow.repositories;

import com.jobinow.model.entities.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Spring Data JPA repository for {@link Badge} entities.
 * Facilitates CRUD operations and data retrieval methods for Badge data.
 */
public interface BadgeRepository extends JpaRepository<Badge, UUID> {
}