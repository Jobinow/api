package com.jobinow.services.spec;

import com.jobinow.model.dto.requests.BadgeRequestDto;
import com.jobinow.model.dto.responses.BadgeResponseDto;

import java.util.UUID;

public interface BadgeService extends _Service<UUID, BadgeRequestDto, BadgeResponseDto> {
}
