package com.jobinow.model.dto.requests;

import com.jobinow.model.entities.Pack;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.URL;

/**
 * DTO for representing a request related to {@link Pack}.
 * This DTO includes information such as the Pack title, description, price, nbOffers ...
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
public record PackRequest(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Description is required")
        String description,

        @Positive(message = "Price must be positive")
        double price,

        @Min(message = "Number of offers must be at least 0", value = 0)
        int nbOffers,

        boolean unlimitedOffers,

        @URL(message = "Image URL must be valid")
        String image
) implements _Request {
}