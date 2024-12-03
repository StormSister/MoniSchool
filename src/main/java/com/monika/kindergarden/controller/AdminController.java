package com.monika.kindergarden.controller;

import com.monika.kindergarden.model.MoniClass;
import com.monika.kindergarden.model.Person;
import com.monika.kindergarden.repository.MoniClassRepository;
import com.monika.kindergarden.repository.PersonRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    private MoniClassRepository moniClassRepository;
    private PersonRepository personRepository;

    @Autowired
    public AdminController(MoniClassRepository moniClassRepository, PersonRepository personRepository) {
        this.moniClassRepository = moniClassRepository;
        this.personRepository = personRepository;
    }

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        List<MoniClass> moniClasses= moniClassRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("classes");
        modelAndView.addObject("moniClass", new MoniClass());
        modelAndView.addObject("moniClasses", moniClasses);
        return modelAndView;
    }

//    @RequestMapping("/displayCourses")
//    public ModelAndView displayCourses(Model model){
//        ModelAndView modelAndView = new ModelAndView("courses");
//        return modelAndView;
//    }

    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("moniClass") @Valid MoniClass moniClass) {
        moniClassRepository.save(moniClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;

    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam int id) {
        Optional<MoniClass> moniClass = moniClassRepository.findById(id);
        for(Person person : moniClass.get().getPersons()){
            person.setMoniClass(null);
            personRepository.save(person);
        }
        moniClassRepository.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;

    }

    @RequestMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId){
        ModelAndView modelAndView = new ModelAndView("students.html");
        return modelAndView;
    }


}
