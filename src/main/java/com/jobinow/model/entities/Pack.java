package com.jobinow.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;

/**
 * Entity class representing a subscription pack.
 */
@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Pack extends AbstractEntity {
    /**
     * The title of the subscription pack.
     */
    @NotBlank(message = "Title is required")
    private String title;

    /**
     * The description of the subscription pack.
     */
    @NotBlank(message = "Description is required")
    private String description;

    /**
     * The price of the subscription pack.
     */
    @Positive(message = "Price must be positive")
    private double price;

    /**
     * The number of offers included in the subscription pack.
     */
    @Min(value = 0, message = "Number of offers must be at least 0")
    private int nbOffers;

    /**
     * Indicates whether the subscription pack has unlimited offers.
     */
    @Column(columnDefinition = "boolean default false")
    private boolean unlimitedOffers = false;

    /**
     * The URL of the image associated with the subscription pack.
     */
    @URL(message = "Image URL must be valid")
    private String image;
}