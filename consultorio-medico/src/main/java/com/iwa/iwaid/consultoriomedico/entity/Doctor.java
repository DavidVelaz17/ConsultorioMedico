package com.iwa.iwaid.consultoriomedico.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

//This is the Doctor Entity where doctor's attributes are stated
//All messages can be found in ValidationMessages.properties under resources
@Data //We are using @Data annotation from projectlombok to create setters and getters automatically
@Entity
@Table(name="doctors")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Doctor {//Class name must be equal as the table name on database, if not a new table with the class name will be created

    //Each attribute with @NotNull or @NotEmpty are obligatory when adding new Doctor's info

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //This is the doctor's ID, we use @Id to specify it and @NotNull to throw and exception if so
    private int id;

    @Column(name="name",length = 45,nullable = false)
    @Size(min = 3,max = 45,message = "{right.length}")
    //This is doctor's name, such cannot be empty, the minimum size is a name of 3 characters and the maximum is 45 characters
    private String name;

    @Column(name="specialty",length = 45,nullable = false)
    @Size(min = 3,max = 45,message = "{right.length}")
    //Doctor's specialization, cannot be empty, the minimum size is a name of 3 characters and the maximum is 45 characters
    private String specialty;

    @Column(name="address",length = 45,nullable = false)
    @Size(min = 3,max = 45,message = "{right.length}")
    //Doctor's address, cannot be empty, the minimum size is a name of 3 characters and the maximum is 45 characters
    private String address;

    @Size(min = 10,max = 13,message = "The minimum length for this field is 10 characters and the maximum 13")
    @Column(name="phone_number",length = 13,nullable = false)
    //Doctor's phone number, cannot be null neither negative
    private String phoneNumber;

    @Column(name="email",length = 45,nullable = false)
    @Email(message="{wrong.email.structure}")
   //Doctor's email, must follow the email structure
    private String email;

    public Doctor(final int id, final String name, final String specialty, final String address, final String phoneNumber, final String email) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


}
