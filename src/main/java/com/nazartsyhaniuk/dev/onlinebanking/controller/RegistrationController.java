package com.nazartsyhaniuk.dev.onlinebanking.controller;

import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;
import com.nazartsyhaniuk.dev.onlinebanking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sign-up")
public class RegistrationController {

    private final CustomerService customerService;

    @Autowired
    public RegistrationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String showRegistrationTemplate(@ModelAttribute("customer") Customer customer, Model model) {
        model.addAttribute("customer", customer);
        return "registration";
    }

    @PostMapping
    public String registration(@Validated Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        customerService.create(customer);

        return "somt";
    }
}