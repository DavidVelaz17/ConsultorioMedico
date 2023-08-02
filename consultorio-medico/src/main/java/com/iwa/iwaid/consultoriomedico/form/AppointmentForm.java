package com.iwa.iwaid.consultoriomedico.form;

import com.iwa.iwaid.consultoriomedico.entity.Hour;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.jsondoc.core.annotation.ApiObjectField;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AppointmentForm implements Serializable {

    @ApiObjectField(name = "patientId", description = "Patient's id")
    @NotNull
    private int patientId;

    @ApiObjectField(name = "doctorId", description = "Doctor's id")
    @NotNull
    private int doctorId;

    @ApiObjectField(name = "date", description = "Appointment's date")
    @NotNull
    private LocalDate date;

    @ApiObjectField(name = "hour", description = "Appointment's hour")
    private Hour hour;

    @ApiObjectField(name = "notes", description = "Appointment's notes")
    private String notes;
}
