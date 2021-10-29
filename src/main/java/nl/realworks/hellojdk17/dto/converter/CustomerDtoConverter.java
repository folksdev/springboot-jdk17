package nl.realworks.hellojdk17.dto.converter;

import nl.realworks.hellojdk17.dto.CustomerDto;
import nl.realworks.hellojdk17.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerDtoConverter {

    CustomerDtoConverter INSTANCE = Mappers.getMapper(CustomerDtoConverter.class);

    @Mapping(target = "customerAddressDtoList", source = "customerAddressSet")
    CustomerDto convertDto(Customer customer);
}
