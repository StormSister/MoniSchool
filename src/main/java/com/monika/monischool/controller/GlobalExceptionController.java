package com.monika.monischool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice(annotations = Controller.class)
public class GlobalExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView handleValidationExceptions(MethodArgumentNotValidException ex) {
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("error");
        StringBuilder errorMessage = new StringBuilder("Validation errors:");
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errorMessage.append(" [").append(error.getField()).append(": ").append(error.getDefaultMessage()).append("]")
        );
        errorPage.addObject("errormsg", errorMessage.toString());
        return errorPage;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handleDatabaseExceptions(DataIntegrityViolationException ex) {
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("error");
        errorPage.addObject("errormsg", "Database error: " + ex.getMessage());
        return errorPage;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("error");
        errorPage.addObject("errormsg", ex.getMessage());
        return errorPage;
    }
}
