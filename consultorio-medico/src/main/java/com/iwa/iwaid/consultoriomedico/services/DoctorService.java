package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.entity.Doctors;
import com.iwa.iwaid.consultoriomedico.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    //@Autowired
    //This service injects the Doctor repository, so we can use JpaRepository querys
    final DoctorRepository doctorRepository;

    //getDoctor method gets a doctor's id sends it to .finById method from JpaRepository and returns a Doctor,
    // it's surrounded with optional because it can be empty or not
    public Optional<Doctors> getDoctor(int id){return doctorRepository.findById(id);}

    //saveDoctor method gets a doctor's info and saves it in database using .save method from JpaRepository
    public void saveDoctor(Doctors doctors){
        doctorRepository.save(doctors);
    }

    //deleteDoctorById gets a doctor's id, and sends it to .deleteById method from JpaRepository
    public void deleteDoctor(int id){
        doctorRepository.deleteById(id);
    }


    public Doctors updateDoctorById(Doctors request,int id){
        //updateDoctorById gets a doctor's id and looks for it with .findById method from JpaRepository, also gets the request
        Doctors doctor=doctorRepository.findById(id).get();
        //Each chunk of data is got by request, and it's set on the Doctor found with id
        //I omit the id field, so it cannot be changed
        doctor.setName(request.getName());
        doctor.setSpecialization(request.getSpecialization());
        doctor.setAddress(request.getAddress());
        doctor.setPhone_number(request.getPhone_number());
        doctor.setEmail(request.getEmail());
        //Finally all doctor's changes are saved with .save method from JpaRepository
        doctorRepository.save(doctor);

        return doctor;
    }

}
