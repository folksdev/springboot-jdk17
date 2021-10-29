package nl.realworks.hellojdk17.dto.converter;

import nl.realworks.hellojdk17.dto.CustomerAddressDto;
import nl.realworks.hellojdk17.model.CustomerAddress;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerAddressDtoConverter {

    CustomerAddressDtoConverter INSTANCE = Mappers.getMapper(CustomerAddressDtoConverter.class);

    CustomerAddressDto convertDto(CustomerAddress customerAddress);
}
