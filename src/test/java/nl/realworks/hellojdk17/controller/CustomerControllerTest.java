package nl.realworks.hellojdk17.controller;

import nl.realworks.hellojdk17.IntegrationTestSupport;
import nl.realworks.hellojdk17.dto.CustomerDto;
import nl.realworks.hellojdk17.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "server-port=0",
        "command.line.runner.enabled=false"
})
@RunWith(SpringRunner.class)
@DirtiesContext
class CustomerControllerTest extends IntegrationTestSupport {


    @Test
    public void testGetCustomerById_WhenCustomerIdExists_shouldReturnCustomerDto() throws Exception {
        Customer customer = customerRepository.save(generateCustomer());
        CustomerDto expected = customerDtoConverter.convertDto(customer);

        this.mockMvc.perform(get(CUSTOMER_API_BASE_URL + customer.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(expected), false))
                .andReturn();
    }

    @Test
    public void testGetCustomerById_WhenCustomerIdDoesNotExists_shouldReturnHttpNotFound() throws Exception {
        this.mockMvc.perform(get(CUSTOMER_API_BASE_URL + "non-exists-customer"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

}