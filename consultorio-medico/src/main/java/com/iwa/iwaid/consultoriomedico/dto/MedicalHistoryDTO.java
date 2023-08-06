package com.iwa.iwaid.consultoriomedico.dto;

import com.iwa.iwaid.consultoriomedico.entity.MedicalHistory;
import com.iwa.iwaid.consultoriomedico.entity.Patient;
import lombok.Builder;
import lombok.Getter;
import org.jsondoc.core.annotation.ApiObjectField;

@Getter
@Builder
public class MedicalHistoryDTO {

    @ApiObjectField(name = "id", description = "Medical history ID")
    private int id;

    @ApiObjectField(name = "patientsId", description = "Patient's ID")
    private int patientsId;

    @ApiObjectField(name = "patient", description = "Patient's Entity")
    private Patient patient;

    @ApiObjectField(name = "height", description = "Patient's height")
    private int height;

    @ApiObjectField(name = "weight", description = "Patient's weight")
    private int weight;

    @ApiObjectField(name = "familyMedicalHistory", description = "Patient's family medical history")
    private boolean familyMedicalHistory;

    @ApiObjectField(name = "specificFamilyMedicalHistory", description = "Patient's specific family medical history")
    private String specificFamilyMedicalHistory;

    @ApiObjectField(name = "pathologicalHistory", description = "Patient's pathological history")
    private boolean pathologicalHistory;

    @ApiObjectField(name = "specificPathologicalHistory", description = "Patient's specific pathological history")
    private String specificPathologicalHistory;

    @ApiObjectField(name = "nonPathologicalHistory", description = "Patient's non pathological history")
    private boolean nonPathologicalHistory;

    @ApiObjectField(name = "specificNonPathologicalHistory", description = "Patient's specific non pathological history")
    private String specificNonPathologicalHistory;

    public static MedicalHistoryDTO build(MedicalHistory medicalHistory) {
        return MedicalHistoryDTO.builder()
                .id(medicalHistory.getId())
                .patientsId(medicalHistory.getPatientsId())
                .patient(medicalHistory.getPatient())
                .height(medicalHistory.getHeight())
                .weight(medicalHistory.getWeight())
                .familyMedicalHistory(medicalHistory.isFamilyMedicalHistory())
                .specificFamilyMedicalHistory(medicalHistory.getSpecificFamilyMedicalHistory())
                .pathologicalHistory(medicalHistory.isPathologicalHistory())
                .specificPathologicalHistory(medicalHistory.getSpecificPathologicalHistory())
                .nonPathologicalHistory(medicalHistory.isNonPathologicalHistory())
                .specificNonPathologicalHistory(medicalHistory.getSpecificNonPathologicalHistory())
                .build();
    }
}
