package ua.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.app.dto.CustomerDTO;
import ua.app.mapper.CustomerDTOMapper;
import ua.app.model.Customer;
import ua.app.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerDTOMapper::toDTO).collect(Collectors.toList());
    }

    public CustomerDTO createCustomer(Customer customer) {
        return CustomerDTOMapper.toDTO(customerRepository.save(customer));
    }

    public CustomerDTO getCustomerById(long id) {
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isPresent()) {
            return CustomerDTOMapper.toDTO(optional.get());
        } else {
            return new CustomerDTO();
        }
    }

    public CustomerDTO updateCustomerById(long id, Customer customer) {
            Optional<Customer> optional = customerRepository.findById(id);
            Customer customerToUpdate;
            if (optional.isPresent()) {
                customerToUpdate = optional.get();
                customerToUpdate.setFullName(customer.getFullName());
                customerToUpdate.setPhone(customer.getPhone());
                return CustomerDTOMapper.toDTO(customerToUpdate);
            } else {
                return new CustomerDTO();
            }

    }

    public void inactivateCustomer (long id){
        Optional<Customer> optional = customerRepository.findById(id);

        if (optional.isPresent()) {
            optional.get().setActive(false);
        }
    }

}
