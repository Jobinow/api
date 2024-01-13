package com.jobinow.model.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jobinow.model.entities.Pack;
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
 * DTO for representing a response related to {@link Pack}.
 * This DTO includes information such as the Pack title, description, price ...
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackResponse extends AbstractResponse {
    @NotBlank(message = "Title is required")
    String title;

    @NotBlank(message = "Description is required")
    String description;

    @Positive(message = "Price must be positive")
    double price;

    @Min(message = "Number of offers must be at least 0", value = 0)
    int nbOffers;

    boolean unlimitedOffers = false;

    @URL(message = "Image URL must be valid")
    String image;
}