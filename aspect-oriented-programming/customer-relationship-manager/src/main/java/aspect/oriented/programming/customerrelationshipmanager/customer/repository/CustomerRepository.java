package aspect.oriented.programming.customerrelationshipmanager.customer.repository;

import aspect.oriented.programming.customerrelationshipmanager.customer.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(
            value = "SELECT c FROM Customer c WHERE c.firstName LIKE %:search%"
    )
    List<Customer> search(@Param("search") String search);
}
