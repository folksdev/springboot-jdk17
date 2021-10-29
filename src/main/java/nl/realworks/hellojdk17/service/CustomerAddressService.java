package nl.realworks.hellojdk17.service;

import nl.realworks.hellojdk17.dto.CustomerAddressDto;
import nl.realworks.hellojdk17.dto.converter.CustomerAddressDtoConverter;
import nl.realworks.hellojdk17.dto.request.CreateCustomerAddressRequest;
import nl.realworks.hellojdk17.dto.request.UpdateCustomerAddressRequest;
import nl.realworks.hellojdk17.exception.CustomerAddressNotFoundException;
import nl.realworks.hellojdk17.model.Customer;
import nl.realworks.hellojdk17.model.CustomerAddress;
import nl.realworks.hellojdk17.repository.CustomerAddressRepository;
import nl.realworks.hellojdk17.util.AddressUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerAddressService {

    private static final CustomerAddressDtoConverter customerAddressDtoConverter = CustomerAddressDtoConverter.INSTANCE;

    private final CustomerAddressRepository customerAddressRepository;
    private final CustomerService customerService;

    public CustomerAddressService(CustomerAddressRepository customerAddressRepository,
                                  CustomerService customerService) {
        this.customerAddressRepository = customerAddressRepository;
        this.customerService = customerService;
    }

    public CustomerAddressDto addCustomerAddress(String customerId,
                                                 CreateCustomerAddressRequest createCustomerAddressRequest) {
        Customer customer = customerService.findCustomerById(customerId);

        CustomerAddress customerAddress = new CustomerAddress(
                createCustomerAddressRequest.city(),
                AddressUtil.yieldCityRegion(createCustomerAddressRequest.city()),
                createCustomerAddressRequest.postcode(),
                createCustomerAddressRequest.streetName(),
                createCustomerAddressRequest.houseNumber(),
                createCustomerAddressRequest.additional(),
                createCustomerAddressRequest.country(),
                customer,
                AddressUtil.generateAddressText(createCustomerAddressRequest));
        return customerAddressDtoConverter.convertDto(customerAddressRepository.save(customerAddress));
    }

    public List<CustomerAddressDto> getCustomerAddressesByCustomerId(String customerId) {
        return customerAddressRepository.findByCustomerId(customerId)
                .stream().map(customerAddressDtoConverter::convertDto)
                .collect(Collectors.toList());
    }

    public CustomerAddressDto getCustomerAddressById(String customerAddressId) {
        return customerAddressDtoConverter.convertDto(findCustomerAddressById(customerAddressId));
    }

    public CustomerAddressDto updateCustomerAddress(String customerAddressId,
                                                    UpdateCustomerAddressRequest updateCustomerAddressRequest) {
        CustomerAddress customerAddress = findCustomerAddressById(customerAddressId);

        CustomerAddress updatedCustomerAddress = new CustomerAddress(
                customerAddress.getId(),
                updateCustomerAddressRequest.city(),
                AddressUtil.yieldCityRegion(updateCustomerAddressRequest.city()),
                updateCustomerAddressRequest.postcode(),
                updateCustomerAddressRequest.streetName(),
                updateCustomerAddressRequest.houseNumber(),
                updateCustomerAddressRequest.additional(),
                updateCustomerAddressRequest.country(),
                customerAddress.getCustomer(),
                AddressUtil.generateAddressText(updateCustomerAddressRequest));


        return customerAddressDtoConverter.convertDto(customerAddressRepository.save(updatedCustomerAddress));
    }

    public void deleteCustomerAddress(String customerAddressId) {
        customerAddressRepository.delete(findCustomerAddressById(customerAddressId));
    }

    private CustomerAddress findCustomerAddressById(String customerAddressId) {
        return customerAddressRepository.findById(customerAddressId)
                .orElseThrow(() -> new CustomerAddressNotFoundException("CustomerAddress with id : " + customerAddressId + " could not found!"));
    }

}
