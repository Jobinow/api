package com.ouharri.Jobinow.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enumeration representing different permissions in the system.
 * Each permission is associated with a specific role, such as recruiter, manager, or super_administrator.
 * This enum provides a convenient way to define and access permissions throughout the application.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public enum Permission {

    /**
     * Permission for reading recruiter-related information.
     */
    recruiter_READ("admin:read"),

    /**
     * Permission for updating recruiter-related information.
     */
    recruiter_UPDATE("admin:update"),

    /**
     * Permission for creating new recruiter-related information.
     */
    recruiter_CREATE("admin:create"),

    /**
     * Permission for deleting recruiter-related information.
     */
    recruiter_DELETE("admin:delete"),

    /**
     * Permission for reading manager-related information.
     */
    MANAGER_READ("management:read"),

    /**
     * Permission for updating manager-related information.
     */
    MANAGER_UPDATE("management:update"),

    /**
     * Permission for creating new manager-related information.
     */
    MANAGER_CREATE("management:create"),

    /**
     * Permission for deleting manager-related information.
     */
    MANAGER_DELETE("management:delete"),

    /**
     * Permission for reading super_administrator related information.
     */
    SUPER_ADMINISTRATOR_READ("admin:read"),

    /**
     * Permission for updating super_administrator related information.
     */
    SUPER_ADMINISTRATOR_UPDATE("admin:update"),

    /**
     * Permission for creating new super_administrator related information.
     */
    SUPER_ADMINISTRATOR_CREATE("admin:create"),

    /**
     * Permission for deleting super_administrator related information.
     */
    SUPER_ADMINISTRATOR_DELETE("admin:delete");

    private final String permission;
}