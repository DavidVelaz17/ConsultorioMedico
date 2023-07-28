package com.iwa.iwaid.consultoriomedico.form;

import com.iwa.iwaid.consultoriomedico.entity.Specialty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.jsondoc.core.annotation.ApiObjectField;

import java.io.Serializable;

@Data
public class DoctorFiltersForm implements Serializable {

    @ApiObjectField(name = "name", description = "Doctor's name")
    private String name;

    @ApiObjectField(name = "specialty", description = "Doctor's specialty")
    private Specialty specialty;

}
