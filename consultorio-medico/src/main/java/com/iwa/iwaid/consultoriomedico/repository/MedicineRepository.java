package com.iwa.iwaid.consultoriomedico.repository;

import com.iwa.iwaid.consultoriomedico.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MedicineRepository extends JpaRepository<Medicine, Integer>, JpaSpecificationExecutor<Medicine> {
}
