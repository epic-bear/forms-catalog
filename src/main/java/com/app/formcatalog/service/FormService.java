package com.app.formcatalog.service;

import com.app.formcatalog.domain.Form;
import com.app.formcatalog.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormService {

    private final FormRepository formRepository;

    @Autowired
    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public List<Form> getAllForms() {
        Sort.Order firstNameOrder = Sort.Order.asc("firstName");
        Sort.Order lastNameOrder = Sort.Order.asc("lastName");
        Sort.Order surnameOrder = Sort.Order.asc("surname");

        Sort sort = Sort.by(firstNameOrder, lastNameOrder, surnameOrder);

        return formRepository.findAll(sort);
    }

    public Form createForm(Form form) {
        return formRepository.save(form);
    }

    public Form updateForm(Form form) {
        Optional<Form> optionalForm = formRepository.findById(form.getId());
        if (optionalForm.isPresent()) {
            Form existingForm = optionalForm.get();
            existingForm.setFirstName(form.getFirstName());
            existingForm.setLastName(form.getLastName());
            existingForm.setSurname(form.getSurname());
            existingForm.setYearOfBirth(form.getYearOfBirth());
            existingForm.setOccupation(form.getOccupation());
            existingForm.setGender(form.getGender());

            return formRepository.save(existingForm);
        } else {
            return null;
        }
    }

    public boolean deleteForm(String id) {
        Optional<Form> optionalForm = formRepository.findById(id);
        if (optionalForm.isPresent()) {
            formRepository.delete(optionalForm.get());
            return true;
        } else {
            return false;
        }
    }
}