package com.iwa.iwaid.consultoriomedico.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.jsondoc.core.annotation.ApiObjectField;

import java.io.Serializable;

@Data
public class MedicineForm implements Serializable {
    @ApiObjectField(name = "name", description = "Medicine's name")
    @Size(min = 3, max = 45, message = "{right.length}")
    @NotBlank
    private String name;

    @ApiObjectField(name = "dose", description = "Medicine's dose")
    @Size(min = 3, max = 45, message = "{right.length}")
    @NotBlank
    private String dose;

    @ApiObjectField(name = "packaging", description = "Medicine's packaging")
    @Size(min = 3, max = 20, message = "The minimum length for this field is 3 characters and maximum 20")
    @NotBlank
    private String packaging;

    @ApiObjectField(name = "description", description = "Medicine's description")
    @Size(max = 60, message = "The maximum length for this field is 60")
    private String description;
}
