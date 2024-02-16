package com.jobinow.services.impl;

import com.jobinow.mapper.BadgeMapper;
import com.jobinow.model.dto.requests.BadgeRequestDto;
import com.jobinow.model.dto.responses.BadgeResponseDto;
import com.jobinow.model.entities.Badge;
import com.jobinow.repositories.BadgeRepository;
import com.jobinow.services.spec.BadgeService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BadgeServiceImpl extends _ServiceImp<UUID, BadgeRequestDto, BadgeResponseDto, Badge, BadgeRepository, BadgeMapper> implements BadgeService {
}
