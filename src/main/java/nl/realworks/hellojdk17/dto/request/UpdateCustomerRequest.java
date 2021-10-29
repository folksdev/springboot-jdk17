package nl.realworks.hellojdk17.dto.request;

public record UpdateCustomerRequest(String name,
                                    String lastName,
                                    Integer birthday) {
}
