package com.iwa.iwaid.consultoriomedico.dto;

import com.iwa.iwaid.consultoriomedico.entity.Appointment;
import lombok.Builder;
import lombok.Data;
import org.jsondoc.core.annotation.ApiObjectField;

import java.time.LocalDateTime;

@Data
@Builder
public class AppointmentDTO {
    @ApiObjectField(name="id",description = "Appointment's id")
    private int id;

    @ApiObjectField(name = "patientId", description = "Patient's id")
    private int patientId;

    @ApiObjectField(name = "doctorId", description = "Doctor's id")
    private int doctorId;

    @ApiObjectField(name = "dateAndTime", description = "Appointment's date and time")
    private LocalDateTime dateAndTime;

    @ApiObjectField(name = "notes", description = "Appointment's notes")
    private String notes;

    public static AppointmentDTO build(final Appointment appointment){
        return AppointmentDTO.builder()
                .id(appointment.getId())
                .patientId(appointment.getPatientId())
                .doctorId(appointment.getDoctorId())
                .dateAndTime(appointment.getDateAndTime())
                .notes(appointment.getNotes())
                .build();
    }
}
