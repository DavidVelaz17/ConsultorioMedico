package com.iwa.iwaid.consultoriomedico.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.jsondoc.core.annotation.ApiObjectField;

import java.io.Serializable;

@Data
public class DoctorForm implements Serializable {
    @ApiObjectField(name="id",description = "Doctor's id")
    private int id;

    @ApiObjectField(name="name",description = "Doctor's name")
    @Size(min = 3,max = 45,message = "{right.length}")
    private String name;

    @ApiObjectField(name="specialty",description = "Doctor's specialty")
    @Size(min = 3,max = 45,message = "{right.length}")
    private String specialty;

    @ApiObjectField(name="address",description = "Doctor's address")
    @Size(min = 3,max = 45,message = "{right.length}")
    private String address;

    @ApiObjectField(name="phoneNumber",description = "Doctor's phoneNumber")
    @Size(min = 10,max = 13,message = "The minimum length for this field is 10 characters and the maximum 13")
    private String phoneNumber;

    @ApiObjectField(name="email",description = "Doctor's email ")
    @Email(message="{wrong.email.structure}")
    private String email;
}
