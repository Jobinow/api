package com.jobinow.controllers;

import com.jobinow.model.dto.requests.OffreRequest;
import com.jobinow.model.dto.responses.OffreResponse;
import com.jobinow.services.spec.OffreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequestMapping("api/v2/offre")
public class OffreController extends _Controller<UUID, OffreRequest, OffreResponse, OffreService> {
}