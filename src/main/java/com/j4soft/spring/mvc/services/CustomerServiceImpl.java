package com.j4soft.spring.mvc.services;

import com.j4soft.spring.mvc.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jacques on 15/01/16.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<Integer, Customer> customers;

    public CustomerServiceImpl() {
        initCustomers();
    }

    private void initCustomers() {
        customers = new HashMap<>();

        for (int index=1; index < 11; index++) {
            Customer customer = new Customer();
            customer.setId(index);
            customer.setFirstName(String.format("First name%s", index));
            customer.setLastName(String.format("Last name%s", index));
            customer.setEmail(String.format("firstname%s.lastname%s@email.com", index, index));
            customer.setPhone(String.format("123-456789-%s", index));
            customer.setAddress1(String.format("address%s line1", index));
            customer.setAddress2(String.format("address%s line2", index));
            customer.setCity(String.format("City%s", index));
            customer.setState(String.format("State%s", index));
            customer.setZipCode(String.format("zipcode%s", index));
            customers.put(customer.getId(), customer);
        }
    }

    private Integer getNextId() {
        return Collections.max(customers.keySet()) + 1;
    }

    @Override
    public Customer saveOrUpdateCustomer(Customer customer) {
        if (customer != null) {
            if (customer.getId() == null) {
                customer.setId(getNextId());
            }
            customers.put(customer.getId(), customer);
            return customer;
        } else {
            throw new RuntimeException("customer cannot be null.");
        }
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customers.get(id);
    }

    @Override
    public void deleteCustomer(Integer id) {
        customers.remove(id);
    }

    @Override
    public List<Customer> listAllCustomers() {
        return new ArrayList<>(customers.values());
    }
}
