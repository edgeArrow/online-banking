package com.nazartsyhaniuk.dev.onlinebanking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mainMenu")
public class MenuController {

    @GetMapping
    public String showMenu() {
        return "main";
    }
}
