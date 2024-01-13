package com.jobinow.repositories;

import com.jobinow.model.entities.Offer;
import com.jobinow.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for the {@link Offer} entity.
 * This repository provides methods for performing CRUD operations on the {@link Offer} entity
 * and includes custom query methods for retrieving offers based on different criteria.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {
    /**
     * Retrieves a list of offers that a specific job seeker has applied to.
     *
     * @param jobSeekerId The unique identifier of the job seeker.
     * @return A {@link List} of {@link Offer} objects that the job seeker has applied to.
     */
    @Query("SELECT Offer FROM Offer Offer " +
            "JOIN Apply apply ON apply.offer.id = Offer.id " +
            "JOIN User jobSeeker ON apply.jobSeeker.id = jobSeeker.id " +
            "WHERE jobSeeker.id = :jobSeekerId")
    List<Offer> findJobSeekerAppliedToOffers(UUID jobSeekerId);

    /**
     * Retrieves a list of offers with titles similar to the provided query.
     *
     * @param title The query string to match against offer titles.
     * @return A {@link List} of {@link Offer} objects with titles similar to the provided query.
     */
    List<Offer> findAllByTitleLike(String title);

    /**
     * Counts the total number of offers associated with a specific recruiter.
     *
     * @param recruiter The {@link User} representing the recruiter.
     * @return The total number of offers associated with the specified recruiter.
     */
    int countAllByRecruiter(User recruiter);
}