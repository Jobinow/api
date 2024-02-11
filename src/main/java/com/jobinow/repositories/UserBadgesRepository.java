package com.jobinow.repositories;

import com.jobinow.model.entities.UserBadges;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Spring Data JPA repository for {@link UserBadges} entities.
 * Responsible for CRUD operations and data retrieval methods related to UserBadges.
 */
public interface UserBadgesRepository extends JpaRepository<UserBadges, UUID> {
}