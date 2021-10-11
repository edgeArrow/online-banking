package com.nazartsyhaniuk.dev.onlinebanking.controller;

import com.nazartsyhaniuk.dev.onlinebanking.dto.CustomerLoginDto;
import com.nazartsyhaniuk.dev.onlinebanking.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String showLoginTemplate(Model model) {
        CustomerLoginDto customerLoginDto = new CustomerLoginDto();
        model.addAttribute("customer", customerLoginDto);
        return "login";
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> signIn(
            @ModelAttribute("customer") @Valid CustomerLoginDto customerLoginDto) {
        return loginService.signIn(customerLoginDto);
    }

}
