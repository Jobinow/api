package com.jobinow.controllers;

import com.jobinow.model.dto.requests.BadgeRequestDto;
import com.jobinow.model.dto.responses.BadgeResponseDto;
import com.jobinow.services.spec.BadgeService;

import java.util.UUID;

public class BadgeController extends _Controller<UUID, BadgeRequestDto, BadgeResponseDto, BadgeService> {
}
