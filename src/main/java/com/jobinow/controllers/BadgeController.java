package com.jobinow.controllers;

import com.jobinow.model.dto.requests.BadgeRequestDto;
import com.jobinow.model.dto.responses.BadgeResponseDto;
import com.jobinow.services.spec.BadgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/badges")
public class BadgeController extends _Controller<UUID, BadgeRequestDto, BadgeResponseDto, BadgeService> {
    private final BadgeService service;

    @GetMapping("/user/{id}")
    public ResponseEntity<Set<BadgeResponseDto>> getUserBadges(@PathVariable UUID id) {
        return ResponseEntity.ok(
                this.service.getBadgesByUserId(id)
        );
    }
}
