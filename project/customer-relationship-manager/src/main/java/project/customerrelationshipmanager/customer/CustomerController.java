package project.customerrelationshipmanager.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.customerrelationshipmanager.customer.dto.Customer;
import project.customerrelationshipmanager.customer.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomer(@RequestParam(value = "search", required = false) String search, Model model) {
        // Get customer from DAO
        List<Customer> customers = this.customerService.getCustomers(search);

        // Add customer to the model
        model.addAttribute("customers", customers);

        return "customer/list-customer";
    }

    @GetMapping("/add")
    public String addCustomer(Model model) {

        return "customer/customer-form";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute Customer customer) {

        this.customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable int id, Model model) {

        Customer customer = this.customerService.getCustomer(id);

        if (customer == null) {
            return "redirect:/customer/list";
        }

        model.addAttribute("customer", customer);

        return "customer/customer-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable int id) {
        this.customerService.deleteCustomer(id);
        return "redirect:/customer/list";
    }
}
