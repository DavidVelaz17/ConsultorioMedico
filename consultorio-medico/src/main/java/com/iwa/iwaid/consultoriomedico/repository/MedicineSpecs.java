package com.iwa.iwaid.consultoriomedico.repository;

import com.iwa.iwaid.consultoriomedico.entity.Medicine;
import com.iwa.iwaid.consultoriomedico.form.MedicineFiltersForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MedicineSpecs {
    public static Specification<Medicine> getAllByFilters(final MedicineFiltersForm form) {
        return (Root<Medicine> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (form.getCode() != null) {
                predicates.add(criteriaBuilder.like(root.get("key"), "%" + form.getCode() + "%"));
            }
            if (form.getName() != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + form.getName() + "%"));
            }
            if (form.getDosageForms() != null) {
                predicates.add(criteriaBuilder.equal(root.get("dosageForms"), form.getDosageForms()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
