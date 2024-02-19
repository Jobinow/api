package com.jobinow.services.impl;

import com.jobinow.exceptions.ResourceNotFoundException;
import com.jobinow.mapper.BadgeMapper;
import com.jobinow.model.dto.requests.BadgeRequestDto;
import com.jobinow.model.dto.responses.BadgeResponseDto;
import com.jobinow.model.entities.Badge;
import com.jobinow.model.entities.Offer;
import com.jobinow.repositories.BadgeRepository;
import com.jobinow.services.spec.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BadgeServiceImpl extends _ServiceImp<UUID, BadgeRequestDto, BadgeResponseDto, Badge, BadgeRepository, BadgeMapper> implements BadgeService {
    private final BadgeRepository repository;
    private final BadgeMapper mapper;

    @Override
    public Set<BadgeResponseDto> getBadgesByUserId(UUID userId) {
        Set<Badge> userBadges = repository.getBadgesByJobSeeker(userId);
        if (userBadges.isEmpty())
            throw new ResourceNotFoundException("User with id " + userId + " had no badges yet");
        return userBadges.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toSet());
    }
}
