package com.app.formcatalog.controller;

import com.app.formcatalog.domain.Form;
import com.app.formcatalog.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class FormController {

    private final FormService formService;

    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping("/form")
    public String getFormPage(@ModelAttribute("form") Form form, Model model) {
        model.addAttribute("form", form);
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

    @GetMapping("form/{id}")
    public ResponseEntity<Form> getFormById(@PathVariable String id) {
        Form form = formService.getFormById(id);
        if (form != null) {
            return new ResponseEntity<>(form, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/form")
    public String saveForm(@ModelAttribute("form") Form form, RedirectAttributes redirectAttributes) {
        if (form.getId() != null) {
            // Form ID exists, perform update operation
            formService.updateForm(form);
            redirectAttributes.addFlashAttribute("message", "Form updated successfully!");
        } else {
            // Form ID doesn't exist, perform create operation
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
            redirectAttributes.addFlashAttribute("error", "Failed to delete the form.");
        }
        return "redirect:/";
    }
}
