package com.jobinow.services.impl;

import com.jobinow.exceptions.StripeCustomException;
import com.jobinow.model.dto.requests.ChargeRequest;
import com.jobinow.services.spec.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the {@link StripeService} interface for handling payments with the Stripe API.
 * This service provides methods for charging users based on the provided {@link ChargeRequest}.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">Ouharri Outman</a>
 */
@Slf4j
@Service
public class StripeServiceImpl implements StripeService {
    /**
     * The secret key for authenticating with the Stripe API.
     */
    @Value("${application.stripe.secret-key}")
    private String secretKey;

    /**
     * Initializes the Stripe API key during bean initialization.
     */
    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
        log.info("Stripe API key initialized");
    }

    /**
     * Charges a user based on the provided {@link ChargeRequest}.
     *
     * @param chargeRequest The {@link ChargeRequest} containing details for the charge operation.
     * @return The {@link Charge} object representing the result of the charge operation.
     * @throws StripeException If an error occurs during the Stripe API call.
     */
    public Charge charge(ChargeRequest chargeRequest) {
        try {
            Map<String, Object> chargeParams = createChargeParams(chargeRequest);
            log.info("Initiating charge with parameters: {}", chargeParams);
            Charge charge = Charge.create(chargeParams);
            log.info("Charge successful: {}", charge);
            return charge;
        } catch (StripeException e) {
            log.error("Error processing payment with Stripe", e);
            throw new StripeCustomException(e.getMessage());
        }
    }

    /**
     * Creates a map of parameters for the Stripe charge based on the provided {@link ChargeRequest}.
     *
     * @param chargeRequest The {@link ChargeRequest} containing details for the charge operation.
     * @return A {@link Map} of parameters for the Stripe charge.
     */
    private Map<String, Object> createChargeParams(ChargeRequest chargeRequest) {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.amount());
        chargeParams.put("currency", chargeRequest.currency());
        chargeParams.put("description", chargeRequest.description());
        chargeParams.put("source", chargeRequest.stripeToken());
        return chargeParams;
    }
}