package com.j4soft.spring.mvc.services;

import com.j4soft.spring.mvc.domain.Customer;

import java.util.List;

/**
 * Created by jacques on 15/01/16.
 */
public interface CustomerService {

    Customer saveOrUpdateCustomer(Customer customer);
    Customer getCustomerById(Integer id);
    void deleteCustomer(Integer id);
    List<Customer> listAllCustomers();

}
