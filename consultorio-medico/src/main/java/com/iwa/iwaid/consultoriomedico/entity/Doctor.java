package com.iwa.iwaid.consultoriomedico.entity;

import com.iwa.iwaid.consultoriomedico.convertors.StringToEnumConvertor;
import com.iwa.iwaid.consultoriomedico.form.DoctorForm;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "doctors")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "specialty", nullable = false)
    @Convert(converter = StringToEnumConvertor.class)
    private Specialty specialty;

    @Column(name = "address", length = 500, nullable = false)
    private String address;

    @Column(name = "phone_number", length = 13, nullable = false)
    private String phoneNumber;

    @Column(name = "email", length = 45, nullable = false)
    private String email;

    public Doctor(final DoctorForm form) {
        this.name = form.getName();
        this.specialty = form.getSpecialty();
        this.address = form.getAddress();
        this.phoneNumber = form.getPhoneNumber();
        this.email = form.getEmail();
    }

    public void updateDoctor(final DoctorForm form) {
        this.name = form.getName();
        this.specialty = form.getSpecialty();
        this.address = form.getAddress();
        this.phoneNumber = form.getPhoneNumber();
        this.email = form.getEmail();
    }
}
