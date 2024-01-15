package com.jobinow.controllers;

import com.jobinow.model.dto.requests.ChargeRequest;
import com.jobinow.model.dto.requests.SubscriptionRequest;
import com.jobinow.model.dto.responses.SubscriptionResponse;
import com.jobinow.services.spec.SubscriptionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

/**
 * Controller class for handling Subscription-related requests.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">Ouharri Outman</a>
 */
@Slf4j
@Validated
@RestController
@RequestMapping("api/v2/subscription")
public class SubscriptionController extends _Controller<UUID, SubscriptionRequest, SubscriptionResponse, SubscriptionService> {

    @PostMapping("/create")
    public ResponseEntity<SubscriptionResponse> create(
            @Valid @RequestBody SubscriptionRequest request,
            @Valid @RequestBody ChargeRequest chargeRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            handleValidationError(bindingResult);

        Optional<SubscriptionResponse> response = service.create(request, chargeRequest);

        return response.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.badRequest().build());
    }
}