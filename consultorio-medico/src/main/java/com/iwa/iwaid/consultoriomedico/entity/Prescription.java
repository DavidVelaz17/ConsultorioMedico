package com.iwa.iwaid.consultoriomedico.entity;

import com.iwa.iwaid.consultoriomedico.form.PrescriptionForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "prescriptions")
@NoArgsConstructor
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "patient_id", nullable = false)
    private int patientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false, insertable = false, updatable = false)
    private Patient patient;

    @Column(name = "doctor_id", nullable = false)
    private int doctorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false, insertable = false, updatable = false)
    private Doctor doctor;

    @Column(name = "register_date")
    private LocalDate registerDate;

    @Column(name = "description")
    private String description;

    public Prescription(final PrescriptionForm form) {
        this.id = form.getId();
        this.patientId = form.getPatientId();
        this.doctorId = form.getDoctorId();
        this.registerDate = form.getRegisterDate();
        this.description = form.getDescription();
    }

    public void updatePrescription(final PrescriptionForm form) {
        this.patientId = form.getPatientId();
        this.doctorId = form.getDoctorId();
        this.registerDate = form.getRegisterDate();
        this.description = form.getDescription();
    }
}

