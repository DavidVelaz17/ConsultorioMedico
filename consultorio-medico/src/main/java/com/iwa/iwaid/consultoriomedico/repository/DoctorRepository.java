package com.iwa.iwaid.consultoriomedico.repository;
import com.iwa.iwaid.consultoriomedico.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
