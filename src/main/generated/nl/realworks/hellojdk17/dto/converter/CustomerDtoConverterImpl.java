package nl.realworks.hellojdk17.dto.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import nl.realworks.hellojdk17.dto.CustomerAddressDto;
import nl.realworks.hellojdk17.dto.CustomerDto;
import nl.realworks.hellojdk17.model.Customer;
import nl.realworks.hellojdk17.model.CustomerAddress;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-26T16:03:41+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17 (Homebrew)"
)
@Component
public class CustomerDtoConverterImpl implements CustomerDtoConverter {

    @Override
    public CustomerDto convertDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        List<CustomerAddressDto> customerAddressDtoList = null;
        String name = null;
        String lastName = null;
        Integer birthday = null;

        customerAddressDtoList = customerAddressSetToCustomerAddressDtoList( customer.getCustomerAddressSet() );
        name = customer.getName();
        lastName = customer.getLastName();
        birthday = customer.getBirthday();

        CustomerDto customerDto = new CustomerDto( name, lastName, birthday, customerAddressDtoList );

        return customerDto;
    }

    protected CustomerAddressDto customerAddressToCustomerAddressDto(CustomerAddress customerAddress) {
        if ( customerAddress == null ) {
            return null;
        }

        String city = null;
        String postcode = null;
        String streetName = null;
        Integer houseNumber = null;
        String additional = null;
        String country = null;

        city = customerAddress.getCity();
        postcode = customerAddress.getPostcode();
        streetName = customerAddress.getStreetName();
        houseNumber = customerAddress.getHouseNumber();
        additional = customerAddress.getAdditional();
        country = customerAddress.getCountry();

        CustomerAddressDto customerAddressDto = new CustomerAddressDto( city, postcode, streetName, houseNumber, additional, country );

        return customerAddressDto;
    }

    protected List<CustomerAddressDto> customerAddressSetToCustomerAddressDtoList(Set<CustomerAddress> set) {
        if ( set == null ) {
            return null;
        }

        List<CustomerAddressDto> list = new ArrayList<CustomerAddressDto>( set.size() );
        for ( CustomerAddress customerAddress : set ) {
            list.add( customerAddressToCustomerAddressDto( customerAddress ) );
        }

        return list;
    }
}
