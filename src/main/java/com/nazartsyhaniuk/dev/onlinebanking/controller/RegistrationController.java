package com.nazartsyhaniuk.dev.onlinebanking.controller;

import com.nazartsyhaniuk.dev.onlinebanking.dto.CustomerDto;
import com.nazartsyhaniuk.dev.onlinebanking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/sign-up")
public class RegistrationController {

    private final CustomerService customerService;

    @Autowired
    public RegistrationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String showRegistrationTemplate(Model model) {
        CustomerDto customerDto = new CustomerDto();
        model.addAttribute("customer", customerDto);
        return "registration";
    }

    @PostMapping
    public String registration(@ModelAttribute("customer") @Valid CustomerDto customerDto,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        customerService.create(customerDto);

        return "main";
    }
}