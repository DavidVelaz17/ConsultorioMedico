package com.iwa.iwaid.consultoriomedico.entity;

import com.iwa.iwaid.consultoriomedico.form.MedicineForm;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "medicines")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @Column(name = "dose", length = 45, nullable = false)
    private String dose;

    @Column(name = "packaging", length = 20, nullable = false)
    private String packaging;

    @Column(name = "description", length = 60, nullable = false)
    private String description;

    public Medicine(final MedicineForm form) {
        this.name = form.getName();
        this.dose = form.getDose();
        this.packaging = form.getPackaging();
        this.description = form.getDescription();
    }

    public void updateMedicine(final MedicineForm form) {
        this.name = form.getName();
        this.dose = form.getDose();
        this.packaging = form.getPackaging();
        this.description = form.getDescription();
    }
}
