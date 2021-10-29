package nl.realworks.hellojdk17.service;

import nl.realworks.hellojdk17.dto.CustomerDto;
import nl.realworks.hellojdk17.dto.converter.CustomerDtoConverter;
import nl.realworks.hellojdk17.dto.request.CreateCustomerRequest;
import nl.realworks.hellojdk17.dto.request.UpdateCustomerRequest;
import nl.realworks.hellojdk17.exception.CustomerNotFoundException;
import nl.realworks.hellojdk17.model.Customer;
import nl.realworks.hellojdk17.model.CustomerAddress;
import nl.realworks.hellojdk17.repository.CustomerRepository;
import nl.realworks.hellojdk17.util.AddressUtil;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private static final CustomerDtoConverter customerDtoConverter = CustomerDtoConverter.INSTANCE;
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest) {

        Customer customer = new Customer(
                createCustomerRequest.name(),
                createCustomerRequest.lastName(),
                createCustomerRequest.birthday());

        Set<CustomerAddress> customerAddressSet = createCustomerRequest.addressList()
                .stream()
                .map(address -> new CustomerAddress(
                        address.city(),
                        AddressUtil.yieldCityRegion(address.city()),
                        address.postcode(),
                        address.streetName(),
                        address.houseNumber(),
                        address.additional(),
                        address.country(),
                        customer,
                        AddressUtil.generateAddressText(address))
                )
                .collect(Collectors.toSet());
        customer.getCustomerAddressSet().addAll(customerAddressSet);
        return customerDtoConverter.convertDto(customerRepository.save(customer));
    }

    public CustomerDto updateCustomer(String customerId, UpdateCustomerRequest updateCustomerRequest) {
        Customer customer = findCustomerById(customerId);

        Customer updatedCustomer = new Customer(
                customer.getId(),
                updateCustomerRequest.name(),
                updateCustomerRequest.lastName(),
                updateCustomerRequest.birthday(),
                customer.getCustomerAddressSet());

        return customerDtoConverter.convertDto(customerRepository.save(updatedCustomer));
    }

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerDtoConverter::convertDto)
                .collect(Collectors.toList());
    }

    public CustomerDto getCustomerById(String customerId) {
        return customerDtoConverter.convertDto(findCustomerById(customerId));
    }

    public void deleteCustomerById(String customerId) {
        customerRepository.delete(findCustomerById(customerId));
    }

    protected Customer findCustomerById(String customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id " + customerId + " could not found"));
    }


}
