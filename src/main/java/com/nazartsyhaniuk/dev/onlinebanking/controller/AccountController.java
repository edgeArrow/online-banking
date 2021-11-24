package com.nazartsyhaniuk.dev.onlinebanking.controller;

import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;
import com.nazartsyhaniuk.dev.onlinebanking.service.CustomerService;
import com.nazartsyhaniuk.dev.onlinebanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final CustomerService customerService;
    private final TransactionService transactionService;

    @Autowired
    public AccountController(CustomerService customerService, TransactionService transactionService) {
        this.customerService = customerService;
        this.transactionService = transactionService;
    }


    @GetMapping("/history")
    public String showHistoryTemplate(Principal principal, Model model) {
        Customer customer = customerService.findByCISNumber(principal.getName());

        model.addAttribute("transactionList", transactionService.findAllByAccount(customer.getAccount()));

        return "transaction-history";
    }
}
