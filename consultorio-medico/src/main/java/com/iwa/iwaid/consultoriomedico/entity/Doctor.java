package com.iwa.iwaid.consultoriomedico.entity;

import com.iwa.iwaid.consultoriomedico.dto.DoctorDTO;
import com.iwa.iwaid.consultoriomedico.form.DoctorForm;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;

@Data
@Entity
@Table(name = "doctors")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @Column(name = "specialty", length = 45, nullable = false)
    private String specialty;

    @Column(name = "address", length = 45, nullable = false)
    private String address;

    @Column(name = "phone_number", length = 13, nullable = false)
    private String phoneNumber;

    @Column(name = "email", length = 45, nullable = false)
    private String email;

    public Doctor(final DoctorForm form) {
        this.id = form.getId();
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
