package com.iwa.iwaid.consultoriomedico.repository;

import com.iwa.iwaid.consultoriomedico.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

/** This is a repository interface for the 'Patient' entity.
 * Patient is a class that represents a table in the database.
 * This interface extends 'JpaRepository' which is an interface from Spring Data JPA.
 */
public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
