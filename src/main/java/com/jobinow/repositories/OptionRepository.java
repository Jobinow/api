package com.jobinow.repositories;

import com.jobinow.model.entities.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Spring Data JPA repository for {@link Option} entities.
 * This interface provides CRUD operations for accessing and manipulating Answer data in the database.
 * It uses UUID as the identifier type for Answer entities.
 */
public interface OptionRepository extends JpaRepository<Option, UUID> {
}