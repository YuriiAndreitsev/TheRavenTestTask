package ua.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.app.model.Customer;

import java.util.List;

@Component
public class CustomerDao {

//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public CustomerDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Customer> getAllCustomers() {
//        return jdbcTemplate.query("SELECT * FROM customer", new BeanPropertyRowMapper<>(Customer.class));
//    }
//
//    public Customer getCustomerById(int id) {
//        return jdbcTemplate.query("SELECT * FROM customer WHERE id=?", new BeanPropertyRowMapper<>(Customer.class),id)
//                .stream().findAny().orElse(null);
//    }
//
//    public void saveCustomer(Customer person) {
//        jdbcTemplate.update("INSERT INTO customer ('full_name', 'email', 'is_active', 'phone', 'created','updated') VALUES(?, ?, ?, ?, ?, ?, ?)", person.getName(), person.getAge(),
//                person.getEmail());
//    }
//
//    public void updateCustomer(int id, Customer updatedCustomer) {
//        jdbcTemplate.update("UPDATE customer SET name=?, age=?, email=? WHERE id=?", updatedPerson.getName(),
//                updatedPerson.getAge(), updatedPerson.getEmail(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM customer WHERE id=?", id);
//    }
}
