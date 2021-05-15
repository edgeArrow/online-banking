package com.nazartsyhaniuk.dev.onlinebanking.controller;

import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;
import com.nazartsyhaniuk.dev.onlinebanking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign-up")
public class RegistrationController {

    private CustomerService customerService;

    @Autowired
    public RegistrationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/registration")
    public Customer registration(@RequestBody Customer customer) {
        customerService.create(customer);

        return customer;
    }
}
