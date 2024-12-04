package com.monika.kindergarden.rest;

import com.monika.kindergarden.model.Contact;
import com.monika.kindergarden.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@Controller
@RequestMapping(path = "/api/contact")
public class ContactRestController {
    ContactRepository contactRepository;

    public ContactRestController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/getMessagesByStatus")
    @ResponseBody
    public List<Contact> getMessagesByStatus(@RequestParam("status") String status) {
        return contactRepository.findAllByStatus(status);
    }
}
