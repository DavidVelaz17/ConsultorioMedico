package com.iwa.iwaid.consultoriomedico.repository;

import com.iwa.iwaid.consultoriomedico.entity.Patient;
import com.iwa.iwaid.consultoriomedico.form.PatientFilterForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PatientSpecs {
    public static Specification<Patient> getAllByFilters(final PatientFilterForm form) {
        return (Root<Patient> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (form.getName() != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + form.getName() + "%"));
            }
            if (form.getGender() != null) {
                predicates.add(criteriaBuilder.equal(root.get("gender"), form.getGender()));
            }
            if (form.getCity() != null) {
                predicates.add(criteriaBuilder.like(root.get("city"), "%" + form.getCity() + "%"));
            }
            if (form.getRfc() != null) {
                predicates.add(criteriaBuilder.like(root.get("rfc"), "%" + form.getRfc() + "%"));
            }
            if (form.getDateOfBirth() != null) {
                if (form.getDateOfBirth().get(0) != null && form.getDateOfBirth().get(1) != null) {
                    predicates.add(criteriaBuilder.between(root.get("dateOfBirth"), form.getDateOfBirth().get(0),
                            form.getDateOfBirth().get(1)));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
