package com.iwa.iwaid.consultoriomedico.entity;

import com.iwa.iwaid.consultoriomedico.convertors.HourConvertor;
import com.iwa.iwaid.consultoriomedico.form.AppointmentForm;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Convert;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "appointments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "patients_Id", nullable = false)
    private int patientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patients_Id", nullable = false, insertable = false, updatable = false)
    private Patient patient;

    @Column(name = "doctors_Id", nullable = false)
    private int doctorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctors_Id", nullable = false, insertable = false, updatable = false)
    private Doctor doctor;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "hour")
    @Convert(converter = HourConvertor.class)
    private Hour hour;

    @Column(name = "notes")
    private String notes;

    public Appointment(final AppointmentForm form) {
        this.patientId = form.getPatientId();
        this.doctorId = form.getDoctorId();
        this.date = form.getDate();
        this.hour = form.getHour();
        this.notes = form.getNotes();
    }

    public void updateAppointment(final AppointmentForm form) {
        this.patientId = form.getPatientId();
        this.doctorId = form.getDoctorId();
        this.date = form.getDate();
        this.hour = form.getHour();
        this.notes = form.getNotes();
    }
}
