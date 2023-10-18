package com.iwa.iwaid.consultoriomedico.form;

import com.iwa.iwaid.consultoriomedico.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.jsondoc.core.annotation.ApiObjectField;

import java.io.Serializable;

@Data
public class UserForm implements Serializable {

    @ApiObjectField(name = "idPatient", description = "Patient's ID")
    private int idPatient;

    @ApiObjectField(name = "idDoctor", description = "Doctors's ID")
    private int idDoctor;

    @ApiObjectField(name = "email", description = "User's email")
    @Email(message = "{wrong.email.structure}")
    private String email;

    @ApiObjectField(name = "password", description = "User's password")
    @Size(max = 100, message = "{name.right.length}")
    private String password;

    @ApiObjectField(name = "role", description = "User's role")
    private Role role;
}
