package com.monika.kindergarden.controller;

import com.monika.kindergarden.model.Courses;
import com.monika.kindergarden.model.MoniClass;
import com.monika.kindergarden.model.Person;
import com.monika.kindergarden.repository.CoursesRepository;
import com.monika.kindergarden.repository.MoniClassRepository;
import com.monika.kindergarden.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
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

    private CoursesRepository coursesRepository;


    @Autowired
    public AdminController(MoniClassRepository moniClassRepository, PersonRepository personRepository, CoursesRepository coursesRepository) {
        this.moniClassRepository = moniClassRepository;
        this.personRepository = personRepository;
        this.coursesRepository = coursesRepository;
    }

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        List<MoniClass> moniClasses = moniClassRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("classes");
        modelAndView.addObject("moniClass", new MoniClass());
        modelAndView.addObject("moniClasses", moniClasses);
        return modelAndView;
    }



    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("moniClass") @Valid MoniClass moniClass) {
        moniClassRepository.save(moniClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;

    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam int id) {
        Optional<MoniClass> moniClass = moniClassRepository.findById(id);
        for (Person person : moniClass.get().getPersons()) {
            person.setMoniClass(null);
            personRepository.save(person);
        }
        moniClassRepository.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;

    }

    @RequestMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession session,
                                        @RequestParam(value = "error", required = false) String error) {
        ModelAndView modelAndView = new ModelAndView("students.html");
        Optional<MoniClass> moniClass = moniClassRepository.findById(classId);
        session.setAttribute("moniClass", moniClass.get());
        modelAndView.addObject("moniClass", moniClass.get());
        modelAndView.addObject("person", new Person());
        if (error != null) {
            String errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute("person") Person person, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        MoniClass moniClass = (MoniClass) session.getAttribute("moniClass");
        Person personEntity = personRepository.readByEmail(person.getEmail());
        if (personEntity == null || !(personEntity.getPersonId() > 0)) {
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + moniClass.getClassId()
                    + "&error=true");
            return modelAndView;
        }
        personEntity.setMoniClass(moniClass);
        personRepository.save(personEntity);
        moniClass.getPersons().add(personEntity);
        moniClassRepository.save(moniClass);
        modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + moniClass.getClassId());
        return modelAndView;
    }

    @RequestMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession session) {
        MoniClass moniClass = (MoniClass) session.getAttribute("moniClass");
        Optional<Person> person = personRepository.findById(personId);
        person.get().setMoniClass(null);
        moniClass.getPersons().remove(person.get());
        moniClassRepository.save(moniClass);
        session.setAttribute("moniClass", moniClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId=" + moniClass.getClassId());
        return modelAndView;
    }

    @RequestMapping("/displayCourses")
    public ModelAndView displayCourses(Model model) {
        List<Courses> courses = coursesRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("courses_secure.html");
        modelAndView.addObject("courses", courses);
        modelAndView.addObject("course", new Courses());
        return modelAndView;
    }

    @PostMapping("/addNewCourse")
    public ModelAndView addNewCourse(Model model, @ModelAttribute("course") Courses course){
        ModelAndView modelAndView = new ModelAndView();
        coursesRepository.save(course);
        modelAndView.setViewName("redirect:/admin/displayCourses");
        return modelAndView;
    }

    @GetMapping("/viewStudents")
    public ModelAndView viewStudents(Model model, @RequestParam int id, HttpSession session,
                                     @RequestParam(required = false) String error){
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("course_students.html");
        Optional<Courses> courses = coursesRepository.findById(id);
        modelAndView.addObject("courses", courses.get());
        modelAndView.addObject("person", new Person());
        session.setAttribute("courses", courses.get());
        if(error != null){
             errorMessage = "Invalid Email entered!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudentToCourse")
    public ModelAndView addStudentToCourse(Model model, @ModelAttribute("person") Person person, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Courses courses = (Courses) session.getAttribute("courses");
        Person personEntity = personRepository.readByEmail(person.getEmail());
        if(personEntity == null || !(personEntity.getPersonId()>0)){
            modelAndView.setViewName("redirect:/admin/viewStudents?id="+courses.getCourseId()
                    + "&error=true");
            return modelAndView;
        }
        personEntity.getCourses().add(courses);
        courses.getPersons().add(personEntity);
        personRepository.save(personEntity);
        session.setAttribute("courses", courses);
        modelAndView.setViewName("redirect:/admin/viewStudents?id="+courses.getCourseId());
        return modelAndView;
    }

    @GetMapping("/deleteStudentFromCourse")
    public ModelAndView deleteStudentFromCourse(Model model, @RequestParam int personId, HttpSession session){
        Courses courses = (Courses) session.getAttribute("courses");
        Optional<Person> person = personRepository.findById(personId);
        person.get().getCourses().remove(courses);
        courses.getPersons().remove(person.get());
        personRepository.save(person.get());
        session.setAttribute("courses", courses);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/viewStudents?id="+courses.getCourseId());
        return modelAndView;
    }

}
