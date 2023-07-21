package com.iwa.iwaid.consultoriomedico.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

//This is the Doctor Entity where doctor's attributes are stated
//All messages can be found in ValidationMessages.properties under resources
@Data //We are using @Data annotation from projectlombok to create setters and getters automatically
@Entity
public class Doctors {//Class name must be equal as the table name on database, if not a new table with the class name will be created

    //Each attribute with @NotNull or @NotEmpty are obligatory when adding new Doctor's info

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //This is the doctor's ID, we use @Id to specify it and @NotNull to throw and exception if so
    private int id;

    @NotEmpty(message = "{not.null.field}")
    @Size(min = 3,max = 45,message = "{right.length}")
    //This is doctor's name, such cannot be empty, the minimum size is a name of 3 characters and the maximum is 45 characters
    private String name;

    @NotEmpty(message = "{not.null.field}")
    @Size(min = 3,max = 45,message = "{right.length}")
    //Doctor's specialization, cannot be empty, the minimum size is a name of 3 characters and the maximum is 45 characters
    private String specialization;

    @NotEmpty(message = "{not.null.field}")
    @Size(min = 3,max = 45,message = "{right.length}")
    //Doctor's address, cannot be empty, the minimum size is a name of 3 characters and the maximum is 45 characters
    private String address;

    @NotNull(message = "{not.null.field}")
    @Positive(message = "{positive.tel.number}")
    //Doctor's phone number, cannot be null neither negative
    private Long phone_number;

   @Email(message="{wrong.email.structure}")
   //Doctor's email, must follow the email structure
    private String email;

    public Doctors() {
    }
    public Doctors(int id, String name, String specialization, String address,Long phone_number, String email) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
    }


}
