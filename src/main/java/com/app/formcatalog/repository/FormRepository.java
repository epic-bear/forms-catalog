package com.app.formcatalog.repository;

import com.app.formcatalog.domain.Form;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormRepository extends MongoRepository<Form, String> {
}
