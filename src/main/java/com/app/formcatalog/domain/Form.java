package com.app.formcatalog.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "forms")
public class Form {
    @Id
    @GeneratedValue
    private String id;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name should contain only letters")
    @Size(max = 20, message = "First name length should not exceed 20 characters")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should contain only letters")
    @Size(max = 20, message = "Last name length should not exceed 20 characters")
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Surname should contain only letters")
    @Size(max = 20, message = "Surname length should not exceed 20 characters")
    private String surname;

    @NotBlank
    @Min(value = 1900, message = "Invalid year of birth")
    @Max(value = 2100, message = "Invalid year of birth")
    private int yearOfBirth;

    @NotBlank
    private Sex sex;
    @NotBlank
    private List<String> hobbies;
}
