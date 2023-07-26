package com.iwa.iwaid.consultoriomedico.entity;

import com.iwa.iwaid.consultoriomedico.form.AppointmentForm;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "appointments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "patients_id")
    private int patientId;

    @Column(name = "doctors_id")
    private int doctorId;

    @Column(name = "date_time")
    private LocalDateTime dateAndTime;

    @Column(name = "notes")
    private String notes;

    public Appointment(final AppointmentForm form) {
        this.patientId = form.getPatientId();
        this.doctorId = form.getDoctorId();
        this.dateAndTime = form.getDateAndTime();
        this.notes = form.getNotes();
    }

    public void updateAppointment(final AppointmentForm form) {
        this.patientId = form.getPatientId();
        this.doctorId = form.getDoctorId();
        this.dateAndTime = form.getDateAndTime();
        this.notes = form.getNotes();
    }

    public void findDoctorAvailability(final AppointmentForm form) {
        this.doctorId = form.getDoctorId();
        this.dateAndTime = form.getDateAndTime();
    }
}
