package com.jobinow.services.impl;

import com.jobinow.model.dto.requests.ProfilRequest;
import com.jobinow.model.dto.responses.ProfilResponse;
import com.jobinow.model.entities.Profil;
import com.jobinow.mapper.ProfilMapper;
import com.jobinow.repositories.ProfilRepository;
import com.jobinow.services.spec.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.UUID;


/**
 * Service implementation for handling operations related to user profiles.
 * This service extends the generic service implementation {@link _ServiceImp}
 * and is specific to the {@link Profil} entity, providing methods for managing profiles.
 * <p>
 * The class leverages the specified repository ({@link ProfilRepository}) and mapper ({@link ProfilMapper})
 * for data access and mapping operations related to the {@link Profil} entity.
 * </p>
 *
 * @version 1.0
 * @see _ServiceImp
 * @see Profil
 * @see ProfilRequest
 * @see ProfilResponse
 * @see ProfilRepository
 * @see ProfilMapper
 */
@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "profil")
public class ProfileServiceImp extends _ServiceImp<UUID, ProfilRequest, ProfilResponse, Profil, ProfilRepository, ProfilMapper> implements ProfileService {
}