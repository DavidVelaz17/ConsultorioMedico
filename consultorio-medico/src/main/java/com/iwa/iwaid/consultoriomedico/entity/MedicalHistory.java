package com.iwa.iwaid.consultoriomedico.entity;

import com.iwa.iwaid.consultoriomedico.form.MedicalHistoryForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "medical_history")
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "patients_id", nullable = false)
    private int patientsId;

    @ManyToOne
    @JoinColumn(name = "patients_id", nullable = false, insertable = false, updatable = false)
    private Patient patient;

    @Column(name = "height")
    private int height;

    @Column(name = "weight")
    private int weight;

    @Column(name = "family_medical_history")
    private boolean familyMedicalHistory;

    @Column(name = "specific_family_medical_history")
    private String specificFamilyMedicalHistory;

    @Column(name = "pathological_history")
    private boolean pathologicalHistory;

    @Column(name = "specific_pathological_history")
    private String specificPathologicalHistory;

    @Column(name = "non_pathological_history")
    private boolean nonPathologicalHistory;

    @Column(name = "specific_non_pathological_history")
    private String specificNonPathologicalHistory;

    public MedicalHistory(final MedicalHistoryForm form) {
        this.id = form.getId();
        this.patientsId = form.getPatientsId();
        this.height = form.getHeight();
        this.weight = form.getWeight();
        this.familyMedicalHistory = form.isFamilyMedicalHistory();
        this.specificFamilyMedicalHistory = form.getSpecificFamilyMedicalHistory();
        this.pathologicalHistory = form.isPathologicalHistory();
        this.specificPathologicalHistory = form.getSpecificPathologicalHistory();
        this.nonPathologicalHistory = form.isNonPathologicalHistory();
        this.specificNonPathologicalHistory = form.getSpecificNonPathologicalHistory();
    }

    public void updateMedicalHistory(final MedicalHistoryForm form) {
        this.patientsId = form.getPatientsId();
        this.height = form.getHeight();
        this.weight = form.getWeight();
        this.familyMedicalHistory = form.isFamilyMedicalHistory();
        this.specificFamilyMedicalHistory = form.getSpecificFamilyMedicalHistory();
        this.pathologicalHistory = form.isPathologicalHistory();
        this.specificPathologicalHistory = form.getSpecificPathologicalHistory();
        this.nonPathologicalHistory = form.isNonPathologicalHistory();
        this.specificNonPathologicalHistory = form.getSpecificNonPathologicalHistory();
    }
}
