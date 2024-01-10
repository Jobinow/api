package com.jobinow.controllers;

import com.jobinow.model.dto.requests.ApplyRequest;
import com.jobinow.model.dto.responses.ApplyResponse;
import com.jobinow.model.dto.responses.UserResponses;
import com.jobinow.services.spec.ApplyService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequestMapping("api/v2/apply")
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
}
