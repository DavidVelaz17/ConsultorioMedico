package com.iwa.iwaid.consultoriomedico.dto;

import com.iwa.iwaid.consultoriomedico.entity.Prescription;
import lombok.Builder;
import lombok.Getter;
import org.jsondoc.core.annotation.ApiObjectField;

import java.time.LocalDate;

@Getter
@Builder
public class PrescriptionDTO{

    @ApiObjectField(name = "id", description = "Prescription's ID")
    private int id;

    @ApiObjectField(name = "PatientId", description = "Patient's ID")
    private int patientId;

    @ApiObjectField(name = "patient", description = "Patient's Data")
    private PatientDTO patient;

    @ApiObjectField(name = "doctorId", description = "Doctor's ID")
    private int doctorId;

    @ApiObjectField(name = "doctor", description = "Doctor's data")
    private DoctorDTO doctor;

    @ApiObjectField(name = "registerDate", description = "Prescription's registration date")
    private LocalDate registerDate;

    @ApiObjectField(name = "description", description = "Prescription's description")
    private String description;


    public static PrescriptionDTO build(final Prescription prescription,
                                        final PatientDTO patient, final DoctorDTO doctor) {
        return PrescriptionDTO.builder()
                .id(prescription.getId())
                .patientId(prescription.getPatientId())
                .patient(patient)
                .doctorId(prescription.getDoctorId())
                .doctor(doctor)
                .registerDate(prescription.getRegisterDate())
                .description(prescription.getDescription())
                .build();
    }

    public static PrescriptionDTO build(final Prescription prescription) {
        return PrescriptionDTO.builder()
                .id(prescription.getId())
                .patientId(prescription.getPatientId())
                .doctorId(prescription.getDoctorId())
                .registerDate(prescription.getRegisterDate())
                .description(prescription.getDescription())
                .build();
    }

}
