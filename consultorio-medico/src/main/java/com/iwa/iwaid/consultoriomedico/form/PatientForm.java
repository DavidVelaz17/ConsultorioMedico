package com.iwa.iwaid.consultoriomedico.form;

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
    @Size(min = 3, max = 30)
    @NotBlank
    @ApiObjectField(name = "name", description = "Patient's name", required = true)
    private String name;


    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiObjectField(name = "dateOfBirth", description = "Patient's date of birth", required = true)
    private LocalDate dateOfBirth;

    @Size(max = 10)
    @ApiObjectField(name = "gender", description = "Patient's gender")
    private String gender;

    @NotBlank
    @ApiObjectField(name = "rfc", description = "Patient's RFC")
    private String rfc;

    @Size(min = 5, max = 80)
    @ApiObjectField(name = "address", description = "Patient's address")
    private String address;

    @Size(max = 45)
    @ApiObjectField(name = "city", description = "Patient's city")
    private String city;

    @Size(min = 12)
    @ApiObjectField(name = "phoneNumber", description = "Patient's phone number")
    private Long phoneNumber;

    @Size(min = 5, max = 45)
    @Email
    @ApiObjectField(name = "email", description = "Patient's mail")
    private String email;
}
