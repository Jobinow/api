package com.jobinow.repositories;

import com.jobinow.model.entities.Pack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Spring Data JPA repository for the {@link Pack} entity.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
public interface PackRepository extends JpaRepository<Pack, UUID> {
}