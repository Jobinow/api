package com.jobinow.services.spec;

import com.jobinow.model.dto.requests.ChargeRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

/**
 * Interface representing a service for interacting with the Stripe payment processing API.
 * Implementing classes should provide functionality for charging a user based on the provided ChargeRequest.
 * This interface defines a method to perform the charge operation.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">Ouharri Outman</a>
 */
public interface StripeService {
    /**
     * Charges a user based on the provided ChargeRequest.
     *
     * @param chargeRequest The ChargeRequest containing details for the charge operation.
     * @return The Charge object representing the result of the charge operation.
     * @throws StripeException If an error occurs during the Stripe API call.
     */
    Charge charge(ChargeRequest chargeRequest);
}