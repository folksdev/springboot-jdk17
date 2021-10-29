package nl.realworks.hellojdk17.util;

import nl.realworks.hellojdk17.dto.request.CreateCustomerAddressRequest;
import nl.realworks.hellojdk17.dto.request.UpdateCustomerAddressRequest;
import nl.realworks.hellojdk17.model.CityRegion;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class AddressUtil {

    public static CityRegion yieldCityRegion(Object city) {
        if (city instanceof String s && !s.isEmpty()) {
            CityRegion cityRegion =
                    switch (s.toLowerCase(Locale.ROOT)) {
                        case "amsterdam", "volendam", "den helder" -> CityRegion.NOORD_HOLLAND;
                        case "roterdam", "the hague", "leiden" -> CityRegion.ZUID_HOLLAND;
                        case "utrecht" -> CityRegion.UTRECHT;
                        case "nijmegen", "renkum" -> {
                            System.out.println("The city from GELDERLAND");
                            yield CityRegion.GELDERLAND;
                        }
                        default -> CityRegion.OTHER;

                    };
            return cityRegion;
        }


        return CityRegion.OTHER;
    }

    public static String generateAddressText(CreateCustomerAddressRequest address) {

        return generateAddressText(new AddressLine(address.streetName(),
                address.houseNumber(),
                address.additional(),
                address.postcode(),
                address.city(),
                address.country()));

    }

    public static String generateAddressText(UpdateCustomerAddressRequest address) {

        return generateAddressText(new AddressLine(address.streetName(),
                address.houseNumber(),
                address.additional(),
                address.postcode(),
                address.city(),
                address.country()));

    }

    private static String generateAddressText(AddressLine address) {

        return """
                Customer address
                %s %s-%s
                %s
                %s, %s
                %s
                """
                .formatted(address.streetName(),
                        address.houseNumber(),
                        address.additional(),
                        address.postcode(),
                        address.city(),
                        yieldCityRegion(address.city()),
                        address.country());

    }

    private record AddressLine(String streetName, Integer houseNumber, String additional, String postcode, String city,
                               String country) {
    }
}
