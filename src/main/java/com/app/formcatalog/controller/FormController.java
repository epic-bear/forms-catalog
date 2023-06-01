package com.app.formcatalog.controller;

import com.app.formcatalog.domain.Form;
import com.app.formcatalog.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class FormController {

    private final FormService formService;

    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping("/new")
    public String showFormPage() {
        return "formPage";
    }

    @GetMapping("/")
    public ModelAndView getAllForms() {
        List<Form> forms = formService.getAllForms();
        ModelAndView modelAndView = new ModelAndView("mainPage");
        modelAndView.addObject("forms", forms);
        return modelAndView;
    }

    @GetMapping("form/{id}")
    public ResponseEntity<Form> getFormById(@PathVariable String id) {
        Form form = formService.getFormById(id);
        if (form != null) {
            return new ResponseEntity<>(form, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Form> createForm(@RequestBody Form form) {
        Form createdForm = formService.createForm(form);
        return new ResponseEntity<>(createdForm, HttpStatus.CREATED);
    }

    @PutMapping("form/{id}")
    public ResponseEntity<Form> updateForm(@PathVariable String id, @RequestBody Form form) {
        Form updatedForm = formService.updateForm(id, form);
        if (updatedForm != null) {
            return new ResponseEntity<>(updatedForm, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("form/{id}")
    public ResponseEntity<Void> deleteForm(@PathVariable String id) {
        boolean deleted = formService.deleteForm(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
