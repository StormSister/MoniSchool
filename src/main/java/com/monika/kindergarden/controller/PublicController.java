package com.monika.kindergarden.controller;

import com.monika.kindergarden.model.Person;
import com.monika.kindergarden.service.PersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("public")
public class PublicController {
    @Autowired
    PersonService personService;

    @RequestMapping("/register")
    public String displayRegisterPage(Model model){
        model.addAttribute("person", new Person());
        return "register.html";
    }

    @PostMapping("/createUser")
    public String createUser(@Valid@ModelAttribute("person") Person person, Errors errors){
        if(errors.hasErrors()){
            return "register.html";
        }

        return "redirect:/login?register=true";
    }


}
