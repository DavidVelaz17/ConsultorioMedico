package com.iwa.iwaid.consultoriomedico.repository;

import com.iwa.iwaid.consultoriomedico.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
}
