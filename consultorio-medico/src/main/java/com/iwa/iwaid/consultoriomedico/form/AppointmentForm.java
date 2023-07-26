package com.iwa.iwaid.consultoriomedico.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.jsondoc.core.annotation.ApiObjectField;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AppointmentForm implements Serializable {

    @ApiObjectField(name = "patientId", description = "Patient's id")
    @NotNull
    private int patientId;

    @ApiObjectField(name = "doctorId", description = "Doctor's id")
    @NotNull
    private int doctorId;

    @ApiObjectField(name = "dateAndTime", description = "Appointment's date and time")
    @NotNull
    private LocalDateTime dateAndTime;

    @ApiObjectField(name = "notes", description = "Appointment's notes")
    private String notes;
}
