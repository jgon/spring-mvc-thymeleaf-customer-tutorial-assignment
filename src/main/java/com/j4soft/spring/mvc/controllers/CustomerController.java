package com.j4soft.spring.mvc.controllers;

import com.j4soft.spring.mvc.domain.Customer;
import com.j4soft.spring.mvc.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jacques on 15/01/16.
 */
@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/customers")
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.listAllCustomers());
        return "customers";
    }

    @RequestMapping("/customer/{id}")
    public String getCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customer";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String saveOrUpdateCustomer(Customer customer) {
        Customer savedCustomer = customerService.saveOrUpdateCustomer(customer);
        return "redirect:/customer/" + savedCustomer.getId();
    }

    @RequestMapping("/customer/new")
    public String newCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "customerForm";
    }

    @RequestMapping("/customer/edit/{id}")
    public String editCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customerForm";
    }

    @RequestMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}