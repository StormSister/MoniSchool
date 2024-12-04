package com.monika.kindergarden.rest;

import com.monika.kindergarden.model.Contact;
import com.monika.kindergarden.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/contact")
public class ContactRestController {

    @Autowired
    ContactRepository contactRepository;

    @GetMapping(value = "/getMessagesByStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Contact> getMessagesByStatus(@RequestParam(name = "status") String status) {
        return contactRepository.findByStatus(status);
    }

    @GetMapping("/getAllMsgsByStatus")
    public List<Contact> getAllMessagesByStatus(@RequestBody Contact contact) {
        if (null != contact && null != contact.getStatus()) {
            return contactRepository.findByStatus(contact.getStatus());
        } else {
            return List.of();
        }
    }
}
