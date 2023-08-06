package com.iwa.iwaid.consultoriomedico.repository;

import com.iwa.iwaid.consultoriomedico.entity.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Integer> {
}
