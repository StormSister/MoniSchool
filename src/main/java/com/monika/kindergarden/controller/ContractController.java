package com.monika.kindergarden.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContractController {
    @RequestMapping("/contact")
    public String displayContactPage(){
        return "contact.html";
    }
}
