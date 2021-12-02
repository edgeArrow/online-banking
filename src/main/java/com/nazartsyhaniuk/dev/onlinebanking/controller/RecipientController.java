package com.nazartsyhaniuk.dev.onlinebanking.controller;

import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Recipient;
import com.nazartsyhaniuk.dev.onlinebanking.service.AccountService;
import com.nazartsyhaniuk.dev.onlinebanking.service.CustomerService;
import com.nazartsyhaniuk.dev.onlinebanking.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class RecipientController {

    private final CustomerService customerService;
    private final RecipientService recipientService;
    private final AccountService accountService;

    @Autowired
    public RecipientController(CustomerService customerService, RecipientService recipientService, AccountService accountService) {
        this.customerService = customerService;
        this.recipientService = recipientService;
        this.accountService = accountService;
    }

    @GetMapping("/recipients")
    public String showAllRecipients(Principal principal, Model model) {
        Customer customer = customerService.findByCISNumber(principal.getName());

        model.addAttribute("allRecipients", recipientService.findAllByAccount(customer.getAccount()));


        return "recipients";
    }

    @GetMapping("/add-recipient")
    public String showAddRecipientTemplate(Model model) {
        model.addAttribute("recipient", new Recipient());

        return "add-recipient";
    }

    @PostMapping("/add-recipient")
    public String addRecipient(@ModelAttribute("recipient") @Valid Recipient recipient, Principal principal,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "recipients";
        }

        if (accountService.checkAccountNumberExist(recipient.getRecipientAccountNumber())) {
            recipientService.create(recipient, principal);
        }

        return "redirect:/recipients";
    }

    @GetMapping("/deleteRecipient/{id}")
    public String deleteRecipient(@PathVariable("id") long id) {
        recipientService.deleteRecipientById(id);

        return "redirect:/recipients";
    }
}