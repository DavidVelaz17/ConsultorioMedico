package com.iwa.iwaid.consultoriomedico.entity;

import com.iwa.iwaid.consultoriomedico.convertors.DosageFormsToIntConvertor;
import com.iwa.iwaid.consultoriomedico.form.MedicineForm;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "medicines")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Medicine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code", length = 5, nullable = false)
    private String code;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "dose", length = 45, nullable = false)
    private String dose;

    @Column(name = "dosageform", nullable = false)
    @Convert(converter = DosageFormsToIntConvertor.class)
    private DosageForm dosageForms;

    @Column(name = "description", length = 60, nullable = false)
    private String description;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public Medicine(final MedicineForm form) {
        this.code = form.getCode();
        this.name = form.getName();
        this.dose = form.getDose();
        this.dosageForms = form.getDosageForms();
        this.description = form.getDescription();
        this.quantity = form.getQuantity();
    }

    public void updateMedicine(final MedicineForm form) {
        this.code = form.getCode();
        this.name = form.getName();
        this.dose = form.getDose();
        this.dosageForms = form.getDosageForms();
        this.description = form.getDescription();
        this.quantity = form.getQuantity();
    }
}
