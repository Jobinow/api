package com.jobinow.model.dto.requests;

import com.jobinow.model.enums.Currency;

/**
 * Record representing a charge request for processing payments.
 */
public record ChargeRequest(
        String description,

        int amount,

        Currency currency,

        String stripeToken
) implements _Request {
}