package com.jobinow.model.dto.responses;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Marker interface for response data transfer objects (DTOs).
 * Implement this interface in all your response DTO classes.
 * This interface serves as a common type for all response DTOs.
 *
 * @param <ID> The type of the identifier used in the DTO.
 *             For example, it could be a UUID or Long.
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 * @version 1.0
 */
public interface _Response<ID> extends Serializable {

    /**
     * Gets the unique identifier of the DTO.
     *
     * @return The identifier of the DTO.
     */
    ID getId();

    /**
     * Gets the timestamp when the DTO was created.
     *
     * @return The creation timestamp of the DTO.
     */
    Timestamp getCreatedAt();

    /**
     * Gets the timestamp when the DTO was last updated.
     *
     * @return The last update timestamp of the DTO.
     */
    Timestamp getUpdatedAt();

    /**
     * Gets the version number of the DTO.
     *
     * @return The version number of the DTO.
     */
    Long getVersion();
}