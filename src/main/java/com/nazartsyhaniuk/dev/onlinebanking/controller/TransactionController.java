package com.nazartsyhaniuk.dev.onlinebanking.controller;

import com.nazartsyhaniuk.dev.onlinebanking.dto.TransactionDto;
import com.nazartsyhaniuk.dev.onlinebanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping
    public String showDepositTemplate(Model model) {
        TransactionDto transactionDto = new TransactionDto();

        model.addAttribute("transaction", transactionDto);

        return "transaction";
    }

    @PostMapping
    public String createTransaction(@ModelAttribute("transaction") @Valid TransactionDto transactionDto,
                                         Principal principal) {
        transactionService.create(transactionDto, principal);

        return "redirect:/mainMenu";
    }
}
