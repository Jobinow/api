package com.jobinow.controllers;

import com.jobinow.model.dto.requests.PackRequest;
import com.jobinow.model.dto.responses.PackResponse;
import com.jobinow.services.spec.PackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Controller class handling API endpoints related to subscription packs.
 * Manages CRUD operations for subscription packs, including creation, retrieval, updating, and deletion.
 * Extends the generic {@link _Controller} class, specifying the types for identifier, request, response, and service.
 *
 * @version 1.0
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 **/
@Slf4j
@Validated
@RestController
@RequestMapping("api/v2/pack")
public class PackController extends _Controller<UUID, PackRequest, PackResponse, PackService> {
}