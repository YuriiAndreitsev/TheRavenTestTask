package ua.app.mapper;

import ua.app.dto.CustomerDTO;
import ua.app.model.Customer;

public class CustomerDTOMapper {
    public static CustomerDTO toDTO(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerDTO customerDTO = new CustomerDTO(customer.getFullName(), customer.getEmail(), customer.getPhone());
        customerDTO.setId(customer.getId());
        return customerDTO;
    }
}
