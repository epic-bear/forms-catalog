package com.app.formcatalog.controller;

import com.app.formcatalog.domain.Form;
import com.app.formcatalog.service.FormService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class FormController {

    private final FormService formService;

    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping("/form")
    public String getFormPage(@ModelAttribute("form") Form form, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("form", form);
        model.addAttribute("errorMessage", redirectAttributes.getFlashAttributes().get("errorMessage"));
        return "formPage";
    }

    @GetMapping("/")
    public ModelAndView getAllForms(RedirectAttributes redirectAttributes) {
        List<Form> forms = formService.getAllForms();
        ModelAndView modelAndView = new ModelAndView("mainPage");
        modelAndView.addObject("forms", forms);

        if (redirectAttributes.getFlashAttributes().containsKey("message")) {
            modelAndView.addObject("message", redirectAttributes.getFlashAttributes().get("message"));
        }

        return modelAndView;
    }

    @PostMapping("/form")
    public String saveForm(@Valid @ModelAttribute("form") Form form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "formPage";
        }
        if (form.getId() != null) {
            formService.updateForm(form);
            redirectAttributes.addFlashAttribute("message", "Form updated successfully!");
        } else {
            formService.createForm(form);
            redirectAttributes.addFlashAttribute("message", "Form created successfully!");
        }
        return "redirect:/";
    }

    @PostMapping("/form/delete")
    public String deleteForm(@ModelAttribute("form") Form form, RedirectAttributes redirectAttributes) {
        boolean deleted = formService.deleteForm(form.getId());
        if (deleted) {
            redirectAttributes.addFlashAttribute("message", "Form deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to delete the form.");
        }
        return "redirect:/";
    }
}
