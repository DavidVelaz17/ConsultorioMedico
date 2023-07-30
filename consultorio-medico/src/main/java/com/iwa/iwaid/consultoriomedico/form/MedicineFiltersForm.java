package com.iwa.iwaid.consultoriomedico.form;

import com.iwa.iwaid.consultoriomedico.entity.DosageForm;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.jsondoc.core.annotation.ApiObjectField;

@Data
public class MedicineFiltersForm {
    @ApiObjectField(name = "key", description = "Medicine's key")
    @Size(max = 5)
    private String key;

    @ApiObjectField(name = "name", description = "Medicine's name")
    @Size(max = 100, message = "{name.right.length}")
    private String name;

    @ApiObjectField(name = "dosageForms", description = "Medicine's dosage forms")
    private DosageForm dosageForms;
}
