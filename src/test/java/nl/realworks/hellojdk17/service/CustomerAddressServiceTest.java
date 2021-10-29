package nl.realworks.hellojdk17.service;

import nl.realworks.hellojdk17.TestSupport;
import nl.realworks.hellojdk17.dto.CustomerAddressDto;
import nl.realworks.hellojdk17.dto.converter.CustomerAddressDtoConverter;
import nl.realworks.hellojdk17.dto.request.CreateCustomerAddressRequest;
import nl.realworks.hellojdk17.exception.CustomerNotFoundException;
import nl.realworks.hellojdk17.model.Customer;
import nl.realworks.hellojdk17.model.CustomerAddress;
import nl.realworks.hellojdk17.repository.CustomerAddressRepository;
import nl.realworks.hellojdk17.util.AddressUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerAddressServiceTest extends TestSupport {

    private static final CustomerAddressDtoConverter customerAddressDtoConverter = CustomerAddressDtoConverter.INSTANCE;

    private CustomerAddressRepository customerAddressRepository;
    private CustomerService customerService;

    private CustomerAddressService customerAddressService;

    @BeforeEach
    public void setup() {
        customerAddressRepository = Mockito.mock(CustomerAddressRepository.class);
        customerService = Mockito.mock(CustomerService.class);

        customerAddressService = new CustomerAddressService(customerAddressRepository, customerService);
    }

    @Test
    public void testAddCustomerAddress_whenCustomerIdExist_shouldSaveAndReturnCustomerAddressDto() {
        Customer customer = generateCustomer();
        CreateCustomerAddressRequest createCustomerAddressRequest = new CreateCustomerAddressRequest("roterdam", "2000 AA", "street2", 20, "B", "netherlands");

        CustomerAddress newCustomerAddress = new CustomerAddress(
                createCustomerAddressRequest.city(),
                AddressUtil.yieldCityRegion(createCustomerAddressRequest.city()),
                createCustomerAddressRequest.postcode(),
                createCustomerAddressRequest.streetName(),
                createCustomerAddressRequest.houseNumber(),
                createCustomerAddressRequest.additional(),
                createCustomerAddressRequest.country(),
                customer,
                AddressUtil.generateAddressText(createCustomerAddressRequest));


        Mockito.when(customerService.findCustomerById("customer-id")).thenReturn(customer);
        Mockito.when(customerAddressRepository.save(newCustomerAddress)).thenReturn(newCustomerAddress);

        CustomerAddressDto result = customerAddressService.addCustomerAddress(customer.getId(), createCustomerAddressRequest);

        assertEquals(result, customerAddressDtoConverter.convertDto(newCustomerAddress));

    }

    @Test
    public void testAddCustomerAddress_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException() {
        Customer customer = generateCustomer();
        CreateCustomerAddressRequest createCustomerAddressRequest = new CreateCustomerAddressRequest("roterdam", "2000 AA", "street2", 20, "B", "netherlands");

        Mockito.when(customerService.findCustomerById("customer-id")).thenThrow(CustomerNotFoundException.class);

        assertThrows(CustomerNotFoundException.class,
                () -> customerAddressService.addCustomerAddress(customer.getId(), createCustomerAddressRequest));

        Mockito.verifyNoInteractions(customerAddressRepository);
    }
}