package nl.realworks.hellojdk17.controller;

import nl.realworks.hellojdk17.dto.CustomerAddressDto;
import nl.realworks.hellojdk17.dto.request.CreateCustomerAddressRequest;
import nl.realworks.hellojdk17.dto.request.UpdateCustomerAddressRequest;
import nl.realworks.hellojdk17.service.CustomerAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/customer-address")
public class CustomerAddressController {

    private final CustomerAddressService customerAddressService;

    public CustomerAddressController(CustomerAddressService customerAddressService) {
        this.customerAddressService = customerAddressService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CustomerAddressDto>> getCustomerAddressesById(@PathVariable String id) {
        return ResponseEntity.ok(customerAddressService.getCustomerAddressesByCustomerId(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CustomerAddressDto>> getCustomerAddressesByCustomerId(@PathVariable String customerId) {
        return ResponseEntity.ok(customerAddressService.getCustomerAddressesByCustomerId(customerId));
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<CustomerAddressDto> addCustomerAddress(@Valid @RequestBody CreateCustomerAddressRequest createCustomerAddressRequest,
                                                                 @PathVariable String customerId){
        return ResponseEntity.ok(customerAddressService.addCustomerAddress(customerId, createCustomerAddressRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerAddressDto> updateCustomerAddress(@Valid @RequestBody UpdateCustomerAddressRequest updateCustomerAddressRequest,
                                                                    @PathVariable String id) {
        return ResponseEntity.ok(customerAddressService.updateCustomerAddress(id, updateCustomerAddressRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerAddress(@PathVariable String id){
        customerAddressService.deleteCustomerAddress(id);
        return ResponseEntity.ok().build();
    }


}
