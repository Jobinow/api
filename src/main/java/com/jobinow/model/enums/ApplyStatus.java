package com.jobinow.model.enums;

/**
 * Enumeration representing different types of application statuses.
 * Applications can have multiple statuses based on recruiters behavior,
 * and this enum defines the possible statuses that the application can have.
 *
 * @version 1.0
 */
public enum ApplyStatus {
    /**
     * Recruiter is satisfied with candidate profile, so he accepted his application.
     */
    ACCEPTED,
    /**
     * Recruiter is not satisfied with candidate profile, so he refused it to inform the candidate the was not accepted.
     */
    REFUSED,
    /**
     * Recruiter has seen the application, but he hasn't decided yet to accept or to refuse it.
     */
    SEEN,
    /**
     * Recruiter has not seen the application yet (default status).
     */
    PENDING
}
