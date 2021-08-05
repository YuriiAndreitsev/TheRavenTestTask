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
    String emailMatcher = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    String phoneNumberMatcher = "^\\+([0-9]{3})([0-9]{9})$";
    String fullNamePattern = "^['A-Za-zА-Яа-я\\s]{2,50}$";
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerDTOMapper::toDTO).collect(Collectors.toList());
    }

    public CustomerDTO createCustomer(Customer customer) {
        if (customer.getEmail().matches(emailMatcher) && isEmailExists(customer.getEmail())) {
            try {
                if (validatePhoneNumber(customer.getPhone()) && validateFullName(customer.getFullName())) {
                    return CustomerDTOMapper.toDTO(customerRepository.save(customer));
                }
            } catch (Exception ex) {
                throw new IllegalArgumentException("Unable to save user in Database");
            }
        }
        return new CustomerDTO();
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
            if (validatePhoneNumber(customer.getPhone()) && validateFullName(customer.getFullName())) {
                customerToUpdate.setFullName(customer.getFullName());
                customerToUpdate.setPhone(customer.getPhone());
                customerToUpdate.setUpdated(System.currentTimeMillis()); //if it is right, simply forgot about it
                return CustomerDTOMapper.toDTO(customerRepository.save(customerToUpdate));
            } else {
                throw new IllegalArgumentException("Unable to UPDATE user in Database");
            }

        } else {
            return new CustomerDTO();
        }

    }

    public void inactivateCustomer(long id) {
        Optional<Customer> optional = customerRepository.findById(id);
        optional.ifPresent(customer -> customer.setActive(false));
    }

    /**
     * Checks the given string as email in db
     *
     * @param email string-email to check in db
     * @return true if there is no such email in db, false if found such email
     */
    public boolean isEmailExists(String email) throws IllegalArgumentException {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email to check is NULL or EMPTY!!!");
        }
        return customerRepository.getCustomerByEmail(email).isEmpty();
    }

    public boolean validatePhoneNumber(String phone) {
        if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("Phone to check is NULL or EMPTY!!!");
        }
        return phone.matches(phoneNumberMatcher);
    }

    public boolean validateFullName(String fullName) {
        if (fullName == null || fullName.isEmpty()) {
            throw new IllegalArgumentException("Full Name to check is NULL or EMPTY!!!");
        }
        return fullName.matches(fullNamePattern);
    }
}
