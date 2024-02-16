package com.jobinow.controllers;

import com.jobinow.model.dto.requests.BadgeRequestDto;
import com.jobinow.model.dto.responses.BadgeResponseDto;
import com.jobinow.services.spec.BadgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/badges")
public class BadgeController extends _Controller<UUID, BadgeRequestDto, BadgeResponseDto, BadgeService> {
}
