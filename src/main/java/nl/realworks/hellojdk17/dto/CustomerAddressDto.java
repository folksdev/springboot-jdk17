package nl.realworks.hellojdk17.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

public record CustomerAddressDto(
        @NotBlank(message = "City value must not be empty")
        String city,
        @NotBlank
        String postcode,
        @NotBlank
        String streetName,
        @PositiveOrZero
        Integer houseNumber,
        String additional,
        @NotBlank
        String country) {
}
