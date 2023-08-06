package com.iwa.iwaid.consultoriomedico.form;

import lombok.Getter;
import lombok.Setter;
import org.jsondoc.core.annotation.ApiObjectField;

import java.io.Serializable;

@Getter
@Setter
public class MedicalHistoryFilterForm implements Serializable {

    @ApiObjectField(name = "patientsId", description = "Patient's ID")
    private int patientsId;

    @ApiObjectField(name = "familyMedicalHistory", description = "Patient's family medical history")
    private boolean familyMedicalHistory;

    @ApiObjectField(name = "pathologicalHistory", description = "Patient's pathological history")
    private boolean pathologicalHistory;

    @ApiObjectField(name = "nonPathologicalHistory", description = "Patient's non pathological history")
    private boolean nonPathologicalHistory;

}
