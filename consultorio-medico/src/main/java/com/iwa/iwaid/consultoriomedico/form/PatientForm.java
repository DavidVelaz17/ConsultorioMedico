package com.iwa.iwaid.consultoriomedico.form;

import com.iwa.iwaid.consultoriomedico.entity.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PatientForm implements Serializable {

    @ApiObjectField(name = "id", description = "Patient's ID")
    private int id;

    @Size(min = 3, max = 100)
    @NotBlank
    @ApiObjectField(name = "name", description = "Patient's name", required = true)
    private String name;

    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiObjectField(name = "dateOfBirth", description = "Patient's birthday", required = true)
    private LocalDate dateOfBirth;

    @Size(max = 10)
    @Enumerated(EnumType.STRING)
    @ApiObjectField(name = "gender", description = "Patient's gender")
    private Gender gender;

    @NotBlank
    @Size(min = 13)
    @ApiObjectField(name = "rfc", description = "Patient's RFC")
    private String rfc;

    @Size(min = 5, max = 500)
    @ApiObjectField(name = "address", description = "Patient's address")
    private String address;

    @Size(max = 500)
    @ApiObjectField(name = "city", description = "Patient's city")
    private String city;

    @Size(min = 13)
    @ApiObjectField(name = "phoneNumber", description = "Patient's phone number")
    private String phoneNumber;

    @Size(min = 5, max = 45)
    @Email
    @ApiObjectField(name = "email", description = "Patient's mail")
    private String email;
}
