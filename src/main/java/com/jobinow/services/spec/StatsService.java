package com.jobinow.services.spec;

import java.util.Map;

/**
 * Service interface for calculating and providing statistical information related to job offers and applications.
 * The methods in this interface allow for the retrieval of statistics such as the number of applications per job offer,
 * the number of job offers per candidate, and general statistics on job offers.
 *
 * <p>
 * Implementations of this interface should perform the necessary calculations using the underlying data of the system.
 * </p>
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 * @version 1.0
 */
public interface StatsService {

    /**
     * Calculate statistics for the number of applications per job offer.
     *
     * @return A map associating the offer ID with the number of applications.
     */
    Map<Object, Long> calculateApplicationsPerOffer();

    /**
     * Calculate statistics for the number of job offers per candidate.
     *
     * @return A map associating the candidate ID with the number of job offers.
     */
    Map<Object, Long> calculateOffersPerCandidate();

    /**
     * Calculate general statistics on job offers.
     *
     * @return A map containing various statistics, such as the total number of offers, the number of approved offers, etc.
     */
    Map<String, Long> calculateGeneralStats();
}