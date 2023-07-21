package com.iwa.iwaid.consultoriomedico.repository;

import com.iwa.iwaid.consultoriomedico.entity.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoctorRepository extends JpaRepository<Doctors, Integer> {
    /*
    This is the Doctor's repository, uses JpaRepository functionality such as findById, save, deleteById
    By using JpaRepository it's no longer required specify each query
     */
}
