package nl.realworks.hellojdk17.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public record CustomerDto(
        @NotBlank
        String name,
        @NotBlank
        String lastName,
        @Min(1900)
        Integer birthday,
        List<CustomerAddressDto> customerAddressDtoList) implements Serializable {

        public CustomerDto(String name, String lastName, Integer birthday) {
                this(name, lastName, birthday, Collections.emptyList());
        }
}
