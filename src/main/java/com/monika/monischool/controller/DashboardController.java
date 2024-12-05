package com.monika.monischool.controller;

import com.monika.monischool.model.Person;
import com.monika.monischool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class DashboardController {

    PersonRepository personRepository;
    @Autowired
    public DashboardController(PersonRepository personRepositoy) {
        this.personRepository = personRepositoy;
    }

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session){
        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("user", person.getName());
        model.addAttribute("roles", authentication.getAuthorities());
        if(null != person.getMoniClass() && null != person.getMoniClass().getName()){
            model.addAttribute("enrolledClass", person.getMoniClass().getName());
        }
        session.setAttribute("loggedInPerson", person);
        logMessages();
        return "dashboard.html";
    }

    private void logMessages() {
        log.error("Error message from the Dashboard page");
        log.warn("Warning message from the Dashboard page");
        log.info("Info message from the Dashboard page");
        log.debug("Debug message from the Dashboard page");
        log.trace("Trace message from the Dashboard page");
    }


}
