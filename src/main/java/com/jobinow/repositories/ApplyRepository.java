package com.jobinow.repositories;

import com.jobinow.model.entities.Apply;
import com.jobinow.model.entities.Offer;
import com.jobinow.model.entities.User;
import com.jobinow.model.enums.ApplyStatus;
import com.jobinow.model.enums.ApplyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for {@link Apply} entities.
 * Extends JpaRepository to inherit common CRUD operations.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Repository
public interface ApplyRepository extends JpaRepository<Apply, UUID> {
    Page<Apply> findAllByJobSeeker(User jobSeeker, Pageable pageable);

    List<Apply> findAllByJobSeeker(User jobSeeker);

    /**
     * Retrieves applies of an offer filtered by apply type (is online of offline)
     *
     * @param offer created by current authenticated recruiter
     * @return list containing applies filtered by apply_type
     */
    List<Apply> getAppliesByOfferAndApplyType(Offer offer, ApplyType applyType);

    /**
     * Retrieves applies of an offer
     *
     * @param offer created by current authenticated recruiter
     * @return list containing applies filtered by apply_type
     */
    List<Apply> getAppliesByOffer(Offer offer);

    /**
     * Update candidate application status to be seen, accepted or refused.
     *
     * @param status The application to be updated.
     */
    @Modifying
    @Transactional
    @Query("update Apply a set a.status = :status where a.id = :id")
    void updateApplyStatus(@Param("status") ApplyStatus status, @Param("id") UUID id);

    /**
     * retrieve statistics about job applications for a certain offer (application date and applications count).
     *
     * @param offerId The application id which needs statistics.
     * @return list of ApplicationStatistics;
     */
    @Query("SELECT COUNT(a), FUNCTION('DATE', a.createdAt) AS applicationDate FROM Apply a WHERE a.offer.id = :offerId GROUP BY FUNCTION('DATE', a.createdAt)")
    List<Object[]> getApplicationStatistics(@Param("offerId") UUID offerId);
}