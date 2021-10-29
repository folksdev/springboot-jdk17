package nl.realworks.hellojdk17.dto.converter;

import javax.annotation.processing.Generated;
import nl.realworks.hellojdk17.dto.CustomerAddressDto;
import nl.realworks.hellojdk17.model.CustomerAddress;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-26T16:03:41+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17 (Homebrew)"
)
public class CustomerAddressDtoConverterImpl implements CustomerAddressDtoConverter {

    @Override
    public CustomerAddressDto convertDto(CustomerAddress customerAddress) {
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
}
