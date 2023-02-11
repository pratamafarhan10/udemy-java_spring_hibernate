package project.customerrelationshipmanager.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.customerrelationshipmanager.customer.dto.Customer;
import project.customerrelationshipmanager.customer.repository.CustomerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void saveCustomer(Customer customer) {
        this.customerRepository.save(customer);
    }

    public Customer getCustomer(int id) {
        Optional<Customer> customerFromDB = this.customerRepository.findById(id);

        return customerFromDB.orElse(null);
    }

    public List<Customer> getCustomers(String search) {

        if (search == null || search.equalsIgnoreCase("")) {
            return this.customerRepository.findAll(Sort.by("firstName"));
        }

        return this.customerRepository.search(search);
    }

    @Transactional
    public void updateCustomer(int id, Customer customer) {
        Optional<Customer> customerFromDB = this.customerRepository.findById(id);

        if (customerFromDB.isPresent()) {
            Customer currentCustomer = customerFromDB.get();

            currentCustomer.updateCustomer(customer);

            // this.customerRepository.save(currentCustomer);
            this.customerRepository.flush();
        }
    }

    public void deleteCustomer(int id) {
        this.customerRepository.deleteById(id);
    }
}
