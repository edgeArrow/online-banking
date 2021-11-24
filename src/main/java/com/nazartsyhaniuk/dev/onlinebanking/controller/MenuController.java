package com.nazartsyhaniuk.dev.onlinebanking.controller;

import com.nazartsyhaniuk.dev.onlinebanking.entity.Account;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;
import com.nazartsyhaniuk.dev.onlinebanking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/mainMenu")
public class MenuController {

    private final CustomerService customerService;

    @Autowired
    public MenuController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping
    public String showMenu(Principal principal, Model model) {
        Customer customer = customerService.findByCISNumber(principal.getName());
        Account account = customer.getAccount();

        model.addAttribute("account", account);

        return "main";
    }
}
