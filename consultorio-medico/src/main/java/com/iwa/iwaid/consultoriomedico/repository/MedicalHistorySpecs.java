package com.iwa.iwaid.consultoriomedico.repository;

import com.iwa.iwaid.consultoriomedico.entity.MedicalHistory;
import org.springframework.data.jpa.domain.Specification;

public class MedicalHistorySpecs {
    public static Specification<MedicalHistory> hasPatientsId(int patientsId) {
        return (root, query, builder) ->
                builder.equal(root.get("patientsId"), patientsId);
    }

    public static Specification<MedicalHistory> hasFamilyMedicalHistory(final boolean familyMedicalHistory) {
        return (root, query, builder) ->
                builder.equal(root.get("familyMedicalHistory"), familyMedicalHistory);
    }

    public static Specification<MedicalHistory> hasPathologicalHistory(final boolean pathologicalHistory) {
        return (root, query, builder) ->
                builder.equal(root.get("pathologicalHistory"), pathologicalHistory);
    }

    public static Specification<MedicalHistory> hasNonPathologicalHistory(final boolean nonPathologicalHistory) {
        return (root, query, builder) ->
                builder.equal(root.get("nonPathologicalHistory"), nonPathologicalHistory);
    }
}
