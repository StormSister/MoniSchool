package com.monika.kindergarden.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class DashboardController {
    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication){
        model.addAttribute("user", authentication.getName());
        model.addAttribute("roles", authentication.getAuthorities());
        log.info(model.toString());
        log.info("User {} accessed dashboard", authentication.getName());
//        throw new RuntimeException("It is a bad day");
        return "dashboard.html";
    }
}
