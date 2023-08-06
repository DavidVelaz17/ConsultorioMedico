package com.iwa.iwaid.consultoriomedico.dto;

import com.iwa.iwaid.consultoriomedico.entity.Appointment;
import com.iwa.iwaid.consultoriomedico.entity.Hour;
import lombok.Builder;
import lombok.Data;
import org.jsondoc.core.annotation.ApiObjectField;

import java.time.LocalDate;

@Data
@Builder
public class AppointmentDTO {
    @ApiObjectField(name = "id", description = "Appointment's id")
    private int id;

    @ApiObjectField(name = "patientId", description = "Patient's id")
    private int patientId;

    @ApiObjectField(name = "patientDTO", description = "Patient's data")
    private PatientDTO patientDTO;

    @ApiObjectField(name = "doctorId", description = "Doctor's id")
    private int doctorId;

    @ApiObjectField(name = "doctorDTO", description = "Doctor's data")
    private DoctorDTO doctorDTO;

    @ApiObjectField(name = "date", description = "Appointment's date")
    private LocalDate date;

    @ApiObjectField(name = "hour", description = "Appointment's hour")
    private Hour hour;

    @ApiObjectField(name = "notes", description = "Appointment's notes")
    private String notes;

    public static AppointmentDTO build(final Appointment appointment,final DoctorDTO doctorDTO, final PatientDTO patientDTO) {
        return AppointmentDTO.builder()
                .id(appointment.getId())
                .patientId(appointment.getPatientId())
                .patientDTO(patientDTO)
                .doctorId(appointment.getDoctorId())
                .doctorDTO(doctorDTO)
                .date(appointment.getDate())
                .hour(appointment.getHour())
                .notes(appointment.getNotes())
                .build();
    }
    public static AppointmentDTO build(final Appointment appointment) {
        return AppointmentDTO.builder()
                .id(appointment.getId())
                .patientId(appointment.getPatientId())
                .doctorId(appointment.getDoctorId())
                .date(appointment.getDate())
                .hour(appointment.getHour())
                .notes(appointment.getNotes())
                .build();
    }
}
