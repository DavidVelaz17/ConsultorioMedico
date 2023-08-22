package com.iwa.iwaid.consultoriomedico.entity;

import com.iwa.iwaid.consultoriomedico.convertors.GenderToIntConvertor;
import com.iwa.iwaid.consultoriomedico.form.PatientForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "patients")
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "gender", nullable = false)
    @Convert(converter = GenderToIntConvertor.class)
    private Gender gender;

    @Column(name = "rfc", nullable = false)
    private String rfc;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    public Patient(final PatientForm form) {
        this.id = form.getId();
        this.name = form.getName();
        this.dateOfBirth = form.getDateOfBirth();
        this.gender = form.getGender();
        this.rfc = form.getRfc();
        this.address = form.getAddress();
        this.city = form.getCity();
        this.phoneNumber = form.getPhoneNumber();
        this.email = form.getEmail();
    }

    public void updatePatient(final PatientForm form) {
        this.name = form.getName();
        this.dateOfBirth = form.getDateOfBirth();
        this.gender = form.getGender();
        this.rfc = form.getRfc();
        this.address = form.getAddress();
        this.city = form.getCity();
        this.phoneNumber = form.getPhoneNumber();
        this.email = form.getEmail();
    }
}
