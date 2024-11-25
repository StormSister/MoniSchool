package com.monika.kindergarden.controller;

import com.monika.kindergarden.model.Contact;
import com.monika.kindergarden.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class ContactController {

    private final ContactService contactService;
    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }


    @GetMapping("/contact")
    public String contactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }


    @PostMapping(value = "/saveMsg")
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors){
        contactService.saveMessageDetails(contact);
        if(errors.hasErrors()){
            log.error("Contact form validation failed", errors.toString());
            return "contact.html";
        }

        return "redirect:/contact";
    }
}
