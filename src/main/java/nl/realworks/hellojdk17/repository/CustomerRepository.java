package nl.realworks.hellojdk17.repository;


import nl.realworks.hellojdk17.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
