package com.iwa.iwaid.consultoriomedico.repository;

import com.iwa.iwaid.consultoriomedico.entity.MedicinesPrescription;
import com.iwa.iwaid.consultoriomedico.entity.MedicinesPrescriptionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicinesPrescriptionRepository extends JpaRepository<MedicinesPrescription, MedicinesPrescriptionId> {
}
