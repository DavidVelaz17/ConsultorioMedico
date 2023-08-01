package com.iwa.iwaid.consultoriomedico.form;

import com.iwa.iwaid.consultoriomedico.entity.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import org.jsondoc.core.annotation.ApiObjectField;

import java.time.LocalDate;
import java.util.List;

@Getter
public class PatientFilterForm {
    @ApiObjectField(name = "name", description = "Name to search")
    String name;
    @Enumerated(EnumType.STRING)
    @ApiObjectField(name = "gender", description = "Patient's gender")
    private Gender gender;
    @ApiObjectField(name = "city", description = "Patient's city")
    private String city;
    @ApiObjectField(name = "dateOfBirth", description = "Patient's date of birth", required = true)
    private List<LocalDate> dateOfBirth;
    @ApiObjectField(name = "rfc", description = "Patient's RFC")
    private String rfc;
}
