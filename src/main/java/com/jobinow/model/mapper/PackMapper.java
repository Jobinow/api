package com.jobinow.model.mapper;

import com.jobinow.model.dto.requests.PackRequest;
import com.jobinow.model.dto.responses.PackResponse;
import com.jobinow.model.entities.Pack;

import java.util.UUID;

/**
 * Mapper interface for converting between {@link PackRequest}, {@link PackResponse}, and {@link Pack} entities.
 * Extends the generic {@link _Mapper} interface with UUID as the identifier type.
 */
public interface PackMapper extends _Mapper<UUID, PackRequest, PackResponse, Pack> {
}
