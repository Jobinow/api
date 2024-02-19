package com.jobinow.repositories;

import com.jobinow.model.dto.responses.BadgeResponseDto;
import com.jobinow.model.entities.Badge;
import com.jobinow.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

/**
 * Spring Data JPA repository for {@link Badge} entities.
 * Facilitates CRUD operations and data retrieval methods for Badge data.
 */
@Repository
public interface BadgeRepository extends JpaRepository<Badge, UUID> {
    @Query("select b from Badge b join b.jobSeekers j where j.id = :userId")
    Set<Badge> getBadgesByJobSeeker(@Param("userId") UUID userId);
}