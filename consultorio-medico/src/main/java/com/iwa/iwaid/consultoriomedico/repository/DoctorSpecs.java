package com.iwa.iwaid.consultoriomedico.repository;

import com.iwa.iwaid.consultoriomedico.entity.Doctor;
import com.iwa.iwaid.consultoriomedico.form.DoctorFiltersForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class DoctorSpecs {
    public static Specification<Doctor> getAllByFilters(final DoctorFiltersForm form) {
        return (Root<Doctor> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (form.getName() != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + form.getName() + "%"));
            }
            if (form.getSpecialty() != null) {
                predicates.add(criteriaBuilder.equal(root.get("specialty"), form.getSpecialty()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
