package com.nazartsyhaniuk.dev.onlinebanking.controller;

import com.nazartsyhaniuk.dev.onlinebanking.dto.CustomerLoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String start() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginTemplate(Model model) {
        CustomerLoginDto customerLoginDto = new CustomerLoginDto();
        model.addAttribute("customer", customerLoginDto);
        return "login";
    }
}
