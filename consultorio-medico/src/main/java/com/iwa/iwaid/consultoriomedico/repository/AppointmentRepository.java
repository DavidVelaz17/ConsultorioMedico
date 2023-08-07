package com.iwa.iwaid.consultoriomedico.repository;

import com.iwa.iwaid.consultoriomedico.entity.Appointment;
import com.iwa.iwaid.consultoriomedico.entity.Hour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    boolean existsByDoctorIdAndDateAndHour(Integer doctorId, LocalDate date, Hour hour);
    boolean existsByPatientIdAndDateAndHour(Integer patientId, LocalDate date, Hour hour);
    boolean existsByDoctorIdAndPatientIdAndDateAndHour(Integer doctorId,Integer patientId, LocalDate date,Hour hour);

}
