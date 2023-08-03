package com.iwa.iwaid.consultoriomedico.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "prescriptions_has_medicaments")
public class MedicinesPrescription {
    @EmbeddedId
    private MedicinesPrescriptionId id;

    @ManyToOne
    @MapsId("prescriptionsId")
    @JoinColumn(name = "prescriptions_id")
    private Prescription prescription;

    @ManyToOne
    @MapsId("medicinesId")
    @JoinColumn(name = "medicines_id")
    private Medicine medicines;

    @Column(name = "quantity")
    private Integer quantity;

    public MedicinesPrescription(Prescription prescription, Medicine medicines, Integer quantity) {
        this.id = new MedicinesPrescriptionId(medicines.getId(), prescription.getId());
        this.prescription = prescription;
        this.medicines = medicines;
        this.quantity = quantity;
    }
}
