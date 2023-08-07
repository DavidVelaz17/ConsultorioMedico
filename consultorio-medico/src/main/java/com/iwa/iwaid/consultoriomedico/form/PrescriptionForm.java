package com.iwa.iwaid.consultoriomedico.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
public class PrescriptionForm implements Serializable {

    @ApiObjectField(name = "id", description = "Prescription's ID")
    private int id;

    @NotNull
    @NumberFormat(style = Style.NUMBER)
    @ApiObjectField(name = "PatientId", description = "Patient's ID")
    private int patientId;

    @NotNull
    @NumberFormat(style = Style.NUMBER)
    @ApiObjectField(name = "doctorId", description = "Doctor's ID")
    private int doctorId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiObjectField(name = "date", description = "Prescription's registration date")
    private LocalDate registerDate;

    @Size(max = 500)
    @ApiObjectField(name = "description", description = "Prescription's description")
    private String description;

}
