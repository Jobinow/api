package com.jobinow.repositories;

import com.jobinow.model.entities.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data JPA repository for the {@link Profil} entity.
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Repository
public interface ProfilRepository extends JpaRepository<Profil, UUID> {
}