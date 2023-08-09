package com.iwa.iwaid.consultoriomedico.form;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.jsondoc.core.annotation.ApiObjectField;

import java.io.Serializable;

@Getter
public class MedicalHistoryForm implements Serializable {

    @ApiObjectField(name = "id", description = "Medical history ID")
    private int id;

    @NotNull
    @ApiObjectField(name = "patientId", description = "Patient's ID")
    private int patientId;

    @NotNull
    @ApiObjectField(name = "height", description = "Patient's height")
    private int height;

    @NotNull
    @ApiObjectField(name = "weight", description = "Patient's weight")
    private int weight;

    @NotNull
    @ApiObjectField(name = "familyMedicalHistory", description = "Patient's family medical history")
    private boolean familyMedicalHistory;

    @Size(max = 500)
    @ApiObjectField(name = "specificFamilyMedicalHistory", description = "Patient's specific family medical history")
    private String specificFamilyMedicalHistory;

    @NotNull
    @ApiObjectField(name = "pathologicalHistory", description = "Patient's pathological history")
    private boolean pathologicalHistory;

    @Size(max = 500)
    @ApiObjectField(name = "specificPathologicalHistory", description = "Patient's specific pathological history")
    private String specificPathologicalHistory;

    @NotNull
    @ApiObjectField(name = "nonPathologicalHistory", description = "Patient's non pathological history")
    private boolean nonPathologicalHistory;

    @Size(max = 500)
    @ApiObjectField(name = "specificNonPathologicalHistory", description = "Patient's specific non pathological history")
    private String specificNonPathologicalHistory;

}
