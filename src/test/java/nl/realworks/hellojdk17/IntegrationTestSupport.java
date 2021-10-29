package nl.realworks.hellojdk17;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import nl.realworks.hellojdk17.dto.converter.CustomerDtoConverter;
import nl.realworks.hellojdk17.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class IntegrationTestSupport extends TestSupport{

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public CustomerRepository customerRepository;

    public CustomerDtoConverter customerDtoConverter = CustomerDtoConverter.INSTANCE;

    public final ObjectMapper mapper = new ObjectMapper();

    public static final String CUSTOMER_API_BASE_URL = "/v1/customer/";

    @BeforeEach
    public void setup() {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

}
