package com.iwa.iwaid.consultoriomedico.dto;

import com.iwa.iwaid.consultoriomedico.entity.Gender;
import com.iwa.iwaid.consultoriomedico.entity.Patient;
import lombok.Builder;
import lombok.Getter;
import org.jsondoc.core.annotation.ApiObjectField;

import java.time.LocalDate;

@Getter
@Builder
public class PatientDTO{
    @ApiObjectField(name = "id", description = "Patient's ID")
    private int id;

    @ApiObjectField(name = "name", description = "Patient's name", required = true)
    private String name;

    @ApiObjectField(name = "dateOfBirth", description = "Patient's date of birth", required = true)
    private LocalDate dateOfBirth;

    @ApiObjectField(name = "gender", description = "Patient's gender")
    private Gender gender;

    @ApiObjectField(name = "rfc", description = "Patient's RFC")
    private String rfc;

    @ApiObjectField(name = "address", description = "Patient's address")
    private String address;

    @ApiObjectField(name = "city", description = "Patient's city")
    private String city;

    @ApiObjectField(name = "phoneNumber", description = "Patient's phone number")
    private String phoneNumber;

    @ApiObjectField(name = "email", description = "Patient's mail")
    private String email;

    @ApiObjectField(name = "password", description = "Doctor's password")
    private String password;

    public static PatientDTO build(final Patient patient){
        return PatientDTO.builder()
                .id(patient.getId())
                .name(patient.getName())
                .dateOfBirth(patient.getDateOfBirth())
                .gender(patient.getGender())
                .rfc(patient.getRfc())
                .address(patient.getAddress())
                .city(patient.getCity())
                .phoneNumber(patient.getPhoneNumber())
                .email(patient.getEmail())
                .password(patient.getPassword())
                .build();
    }
}
