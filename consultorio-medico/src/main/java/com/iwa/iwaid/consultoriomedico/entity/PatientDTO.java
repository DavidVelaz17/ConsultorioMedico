package com.iwa.iwaid.consultoriomedico.entity;

import lombok.Builder;
import lombok.Data;
import org.jsondoc.core.annotation.ApiObjectField;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class PatientDTO implements Serializable {
    @ApiObjectField(name = "id", description = "Patient's ID")
    private int id;

    @ApiObjectField(name = "name", description = "Patient's name", required = true)
    private String name;

    @ApiObjectField(name = "dateOfBirth", description = "Patient's date of birth", required = true)
    private LocalDate dateOfBirth;

    @ApiObjectField(name = "gender", description = "Patient's gender")
    private String gender;

    @ApiObjectField(name = "rfc", description = "Patient's rfc")
    private String rfc;

    @ApiObjectField(name = "address", description = "Patient's address")
    private String address;

    @ApiObjectField(name = "city", description = "Patient's city")
    private String city;

    @ApiObjectField(name = "phoneNumber", description = "Patient's phone number")
    private Long phoneNumber;

    @ApiObjectField(name = "email", description = "Patient's mail")
    private String email;

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
                .build();
    }
}
