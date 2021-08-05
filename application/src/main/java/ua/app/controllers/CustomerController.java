package ua.app.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.app.dto.CustomerDTO;
import ua.app.model.Customer;
import ua.app.service.CustomerService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() throws JsonProcessingException {
        String jsonObj = new ObjectMapper().writeValueAsString(new Customer("asdsad", "sadasd@asdsad", "123123123"));
        System.out.println(jsonObj);
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @PostMapping(value = "/customers", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody Customer customer) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/customers").toUriString());
        return ResponseEntity.created(uri).body(customerService.createCustomer(customer));
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/customers/" + id).toUriString());
        return ResponseEntity.created(uri).body(customerService.getCustomerById(Long.parseLong(id)));
    }

    @PutMapping(value = "/customers/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CustomerDTO> test(@PathVariable String id, @RequestBody Customer customer) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/customers/" + id).toUriString());
        return ResponseEntity.created(uri).body(customerService.updateCustomerById(Long.parseLong(id), customer));
    }

    @DeleteMapping(value = "/customers/{id}")
    public void inactivateUserById(@PathVariable String id) {
        customerService.inactivateCustomer(Long.parseLong(id));

    }
}
