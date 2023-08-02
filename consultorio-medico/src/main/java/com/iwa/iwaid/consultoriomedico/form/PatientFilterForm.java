package com.iwa.iwaid.consultoriomedico.form;

import com.iwa.iwaid.consultoriomedico.entity.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.jsondoc.core.annotation.ApiObjectField;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PatientFilterForm {
    @ApiObjectField(name = "name", description = "Patient's name")
    String name;

    @ApiObjectField(name = "gender", description = "Patient's gender")
    private Gender gender;

    @ApiObjectField(name = "city", description = "Patient's city")
    private String city;

    @ApiObjectField(name = "dateOfBirth", description = "Patient's birthday", required = true)
    private List<LocalDate> dateOfBirth;

    @ApiObjectField(name = "rfc", description = "Patient's RFC")
    private String rfc;
}
