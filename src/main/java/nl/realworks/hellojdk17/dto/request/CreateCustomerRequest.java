package nl.realworks.hellojdk17.dto.request;

import java.util.List;

public record CreateCustomerRequest(String name,
                                    String lastName,
                                    Integer birthday,
                                    List<CreateCustomerAddressRequest> addressList) {
}
