package com.iwa.iwaid.consultoriomedico.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@Entity
@Table(name = "prescriptions")
@NoArgsConstructor
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "patients_id", nullable = false)
    private int patientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patients_id", nullable = false, insertable = false, updatable = false)
    private Patient patient;

    @Column(name = "doctors_id", nullable = false)
    private int doctorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctors_id", nullable = false, insertable = false, updatable = false)
    private Doctor doctor;

    @OneToMany(mappedBy = "prescription")
    private List<MedicinesPrescription> medicinesPrescriptions;

    @Column(name = "date")
    private Date date;
}
