package com.iwa.iwaid.consultoriomedico.form;

import com.iwa.iwaid.consultoriomedico.entity.Specialty;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.jsondoc.core.annotation.ApiObjectField;

import java.io.Serializable;

@Data
public class DoctorForm implements Serializable {

    @ApiObjectField(name = "name", description = "Doctor's name")
    @Size(max = 100, message = "{name.right.length}")
    private String name;

    @ApiObjectField(name = "specialty", description = "Doctor's specialty")
    private Specialty specialty;

    @ApiObjectField(name = "address", description = "Doctor's address")
    @Size(max = 500, message = "{address.right.length}")
    private String address;

    @ApiObjectField(name = "phoneNumber", description = "Doctor's phone number")
    @Size(max = 13, message = "{phoneNumber.right.length}")
    private String phoneNumber;

    @ApiObjectField(name = "email", description = "Doctor's email ")
    @Email(message = "{wrong.email.structure}")
    private String email;
}
