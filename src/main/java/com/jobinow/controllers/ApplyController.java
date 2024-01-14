package com.jobinow.controllers;

import com.jobinow.model.dto.basic.ApplicationStatistics;
import com.jobinow.model.dto.requests.ApplyRequest;
import com.jobinow.model.dto.responses.ApplyResponse;
import com.jobinow.model.dto.responses.OfferResponse;
import com.jobinow.model.dto.responses.UserResponses;
import com.jobinow.model.enums.ApplyType;
import com.jobinow.services.spec.ApplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/applies")
public class ApplyController extends _Controller<UUID, ApplyRequest, ApplyResponse, ApplyService> {
    @PostMapping("/history")
    public ResponseEntity<List<ApplyResponse>> getHistory(@RequestBody @Valid UserResponses jobSeeker) {
        return ResponseEntity.ok(
                this.service.getAllApplies(jobSeeker)
        );
    }

    @PostMapping("/history/paged")
    public ResponseEntity<Page<ApplyResponse>> getHistoryPaged(@RequestBody @Valid UserResponses jobSeeker, Pageable pageable) {
        return ResponseEntity.ok(
                this.service.getAllApplies(jobSeeker, pageable)
        );
    }

    @PostMapping("/filter")
    public ResponseEntity<List<ApplyResponse>> getFilteredApplies(@RequestBody OfferResponse offerResponse, @RequestParam ApplyType applyType) {
        return ResponseEntity.ok(
                this.service.getAppliesByApplyType(offerResponse, applyType)
        );
    }

    @PostMapping("/offer")
    public ResponseEntity<List<ApplyResponse>> getOfferApplies(@RequestBody OfferResponse offerResponse) {
        return ResponseEntity.ok(
                this.service.getOfferApplies(offerResponse)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateApplyStatus(@PathVariable String id, @RequestBody ApplyResponse applyResponse) {
        this.service.updateApplyStatus(id, applyResponse);
        return ResponseEntity.ok(
                "Apply status updated successfully"
        );
    }

    @GetMapping("/statistics/{id}")
    public ResponseEntity<List<ApplicationStatistics>> getApplicationStatistics(@PathVariable String id) {
        return ResponseEntity.ok(this.service.getApplicationStatistics(id));
    }
}
