package com.iwa.iwaid.consultoriomedico.dto;

import com.iwa.iwaid.consultoriomedico.entity.Role;
import com.iwa.iwaid.consultoriomedico.entity.User;
import lombok.Builder;
import lombok.Getter;
import org.jsondoc.core.annotation.ApiObjectField;

@Getter
@Builder
public class UserDTO {

    @ApiObjectField(name = "idPatient", description = "Patient's id")
    private int idPatient;

    @ApiObjectField(name = "idDoctor", description = "Doctor's id")
    private int idDoctor;

    @ApiObjectField(name = "role", description = "User's role")
    private Role role;

    public static UserDTO build(final User user){
        return UserDTO.builder()
                .idPatient(user.getIdPatient())
                .idDoctor(user.getIdDoctor())
                .role(user.getRole())
                .build();
    }
}
