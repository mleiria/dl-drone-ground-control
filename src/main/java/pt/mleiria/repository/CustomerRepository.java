package pt.mleiria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.mleiria.entity.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);
}
