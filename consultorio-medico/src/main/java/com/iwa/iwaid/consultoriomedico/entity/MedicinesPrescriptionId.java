package com.iwa.iwaid.consultoriomedico.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MedicinesPrescriptionId implements Serializable {
    @Column(name = "prescriptions_id")
    private Integer prescriptionsId;

    @Column(name = "medicines_id")
    private Integer medicinesId;
}
