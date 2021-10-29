package nl.realworks.hellojdk17.repository;

import nl.realworks.hellojdk17.model.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, String> {

    List<CustomerAddress> findByCustomerId(String customerId);
}
