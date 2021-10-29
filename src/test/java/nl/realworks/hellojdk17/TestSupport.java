package nl.realworks.hellojdk17;

import nl.realworks.hellojdk17.model.CityRegion;
import nl.realworks.hellojdk17.model.Customer;
import nl.realworks.hellojdk17.model.CustomerAddress;

import java.util.HashSet;
import java.util.Set;

public class TestSupport {


    public static Customer generateCustomer() {
        Customer customer = new Customer(
                "customer-id",
                "first-name",
                "last-name",
                1988,
                new HashSet<>());
        customer.getCustomerAddressSet().add(generateCustomerAddress(customer));
        return customer;

    }

    public static CustomerAddress generateCustomerAddress(Customer customer) {
        return new CustomerAddress(
                "address-id",
                "amsterdam",
                CityRegion.NOORD_HOLLAND,
                "1000 AA",
                "street",
                10,
                "A",
                "country",
                customer,
                """
                        Customer address
                        %s %s-%s
                        %s
                        %s, %s
                        %s
                        """
                        .formatted("street",
                                10,
                                "A",
                                "1000 AA",
                                "amsterdam",
                                CityRegion.NOORD_HOLLAND,
                                "country"));
    }
}
