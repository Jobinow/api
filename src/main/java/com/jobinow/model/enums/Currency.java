package com.jobinow.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enumeration representing commonly used currency types.
 * This enum includes various currency codes along with their human-readable descriptions.
 */
@Getter
@RequiredArgsConstructor
public enum Currency {
    /**
     * United States Dollar (USD) - The official currency of the United States.
     */
    USD("United States Dollar"),

    /**
     * Euro (EUR) - The official currency of the Eurozone.
     */
    EUR("Euro"),

    /**
     * British Pound Sterling (GBP) - The official currency of the United Kingdom.
     */
    GBP("British Pound Sterling"),

    /**
     * Japanese Yen (JPY) - The official currency of Japan.
     */
    JPY("Japanese Yen"),

    /**
     * Swiss Franc (CHF) - The official currency of Switzerland.
     */
    CHF("Swiss Franc"),

    /**
     * Canadian Dollar (CAD) - The official currency of Canada.
     */
    CAD("Canadian Dollar"),

    /**
     * Australian Dollar (AUD) - The official currency of Australia.
     */
    AUD("Australian Dollar"),

    /**
     * Chinese Yuan (CNY) - The official currency of China.
     */
    CNY("Chinese Yuan"),

    /**
     * Indian Rupee (INR) - The official currency of India.
     */
    INR("Indian Rupee"),

    /**
     * Brazilian Real (BRL) - The official currency of Brazil.
     */
    BRL("Brazilian Real");

    /**
     * A human-readable description of the currency.
     */
    private final String description;

}
