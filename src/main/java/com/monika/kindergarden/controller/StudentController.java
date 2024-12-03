package com.monika.kindergarden.controller;

import com.monika.kindergarden.model.Person;
import com.monika.kindergarden.repository.CoursesRepository;
import com.monika.kindergarden.repository.MoniClassRepository;
import com.monika.kindergarden.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

    MoniClassRepository moniClassRepository;

    PersonRepository personRepository;

    CoursesRepository coursesRepository;

    public StudentController(MoniClassRepository moniClassRepository, PersonRepository personRepository,
                             CoursesRepository coursesRepository) {
        this.moniClassRepository = moniClassRepository;
        this.personRepository = personRepository;
        this.coursesRepository = coursesRepository;
    }

    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(Model model, HttpSession session){
        Person person = (Person) session.getAttribute("loggedInPerson");
        ModelAndView modelAndView = new ModelAndView("courses_enrolled.html");
        modelAndView.addObject("person, person");
        return modelAndView;

    }


}
