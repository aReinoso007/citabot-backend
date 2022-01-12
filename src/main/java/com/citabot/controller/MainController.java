package com.citabot.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MainController {
    @RequestMapping("/welcome")
    public String getInit(){
        return "Hola";
    }
}
