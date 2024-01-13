package com.jobinow.services.spec;

import com.jobinow.model.dto.requests.OfferRequest;
import com.jobinow.model.dto.requests.PackRequest;
import com.jobinow.model.dto.responses.OfferResponse;
import com.jobinow.model.dto.responses.PackResponse;

import java.util.UUID;

/**
 * Service implementation class for managing subscription pack.
 * This service extends the generic service interface {@link _Service},
 * specializing in operations related to job pack entities.
 * It defines methods for creating, retrieving, and handling
 * other aspects of job offer data.
 * <p>
 * The interface is designed to work with UUID as the identifier type for job offer entities.
 * </p>
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 * @version 1.0
 * @see OfferRequest
 * @see OfferResponse
 * @see _Service
 */
public interface PackService extends _Service<UUID, PackRequest, PackResponse> {
}