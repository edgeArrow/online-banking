package com.nazartsyhaniuk.dev.onlinebanking.controller;

import com.nazartsyhaniuk.dev.onlinebanking.dto.CustomerLoginDto;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;
import com.nazartsyhaniuk.dev.onlinebanking.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String showLoginTemplate(@ModelAttribute("customer") Customer customer, Model model) {
        model.addAttribute("customer", customer);
        return "login";
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> signIn(CustomerLoginDto customer) {
        return loginService.signIn(customer);
    }

}
