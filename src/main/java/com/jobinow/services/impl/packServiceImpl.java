package com.jobinow.services.impl;

import com.jobinow.model.dto.requests.OfferRequest;
import com.jobinow.model.dto.requests.PackRequest;
import com.jobinow.model.dto.responses.OfferResponse;
import com.jobinow.model.dto.responses.PackResponse;
import com.jobinow.model.entities.Pack;
import com.jobinow.mapper.PackMapper;
import com.jobinow.repositories.PackRepository;
import com.jobinow.services.spec.PackService;
import com.jobinow.services.spec._Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

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
 * @version 1.0
 * @see OfferRequest
 * @see OfferResponse
 * @see _Service
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "pack")
public class packServiceImpl extends _ServiceImp<UUID, PackRequest, PackResponse, Pack, PackRepository, PackMapper> implements PackService {
}