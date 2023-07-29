package com.iwa.iwaid.consultoriomedico.form;

import com.iwa.iwaid.consultoriomedico.entity.DosageForm;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.jsondoc.core.annotation.ApiObjectField;

import java.io.Serializable;

@Data
public class MedicineForm implements Serializable {

    @ApiObjectField(name = "key", description = "Medicine's key")
    @Size(min = 3, max = 5)
    @NotBlank
    private String key;

    @ApiObjectField(name = "name", description = "Medicine's name")
    @Size(min = 3, max = 100, message = "{right.length}")
    @NotBlank
    private String name;

    @ApiObjectField(name = "dose", description = "Medicine's dose")
    @Size(min = 3, max = 45, message = "{right.length}")
    @NotBlank
    private String dose;

    @ApiObjectField(name = "dosageForms", description = "Medicine's dosage forms")
    @NotBlank
    private DosageForm dosageForms;

    @ApiObjectField(name = "description", description = "Medicine's description")
    @Size(max = 60, message = "The maximum length for this field is 60")
    private String description;
}
