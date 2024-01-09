package com.jobinow.repositories;

import com.jobinow.model.entities.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for {@link Apply} entities.
 * Extends JpaRepository to inherit common CRUD operations.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Repository
public interface ApplyRepository extends JpaRepository<Apply, UUID> {
}