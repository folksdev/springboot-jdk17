package nl.realworks.hellojdk17.dto.request;

public record UpdateCustomerAddressRequest(String city,
                                           String postcode,
                                           String streetName,
                                           Integer houseNumber,
                                           String additional,
                                           String country) {
}
