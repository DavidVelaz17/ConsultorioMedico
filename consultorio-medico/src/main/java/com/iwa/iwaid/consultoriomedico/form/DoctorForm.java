package com.iwa.iwaid.consultoriomedico.form;

import com.iwa.iwaid.consultoriomedico.entity.Specialty;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.jsondoc.core.annotation.ApiObjectField;

import java.io.Serializable;

@Data
public class DoctorForm implements Serializable {

    @ApiObjectField(name = "name", description = "Doctor's name")
    @Size(min = 3, max = 100, message = "{name.right.length}")
    @NotBlank
    private String name;

    @ApiObjectField(name = "specialty", description = "Doctor's specialty")
    @NotNull
    private Specialty specialty;

    @ApiObjectField(name = "address", description = "Doctor's address")
    @Size(min = 3, max = 500, message = "{address.right.length}")
    @NotBlank
    private String address;

    @ApiObjectField(name = "phoneNumber", description = "Doctor's phone number")
    @Size(min = 10, max = 13, message = "{phoneNumber.right.length}")
    @NotBlank
    private String phoneNumber;

    @ApiObjectField(name = "email", description = "Doctor's email ")
    @Email(message = "{wrong.email.structure}")
    @NotBlank
    private String email;
}
