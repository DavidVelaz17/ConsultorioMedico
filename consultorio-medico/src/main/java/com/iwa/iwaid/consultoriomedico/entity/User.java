package com.iwa.iwaid.consultoriomedico.entity;

import com.iwa.iwaid.consultoriomedico.dto.DoctorDTO;
import com.iwa.iwaid.consultoriomedico.dto.PatientDTO;
import com.iwa.iwaid.consultoriomedico.form.PatientForm;
import com.iwa.iwaid.consultoriomedico.form.UserForm;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "id_patient",nullable = true)
    private int idPatient;

    @Column(name = "id_doctor",nullable = true)
    private int idDoctor;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "role",nullable = false)
    private Role role;

    public User(final PatientDTO form) {
        this.idPatient = form.getId();
        this.email = form.getEmail();
        this.password = form.getPassword();
        this.role = Role.Patient;
    }

    public User(final DoctorDTO form) {
        this.idDoctor = form.getId();
        this.email = form.getEmail();
        this.password = form.getPassword();
        this.role = Role.Doctor;
    }

}
