package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.entity.Doctor;
import com.iwa.iwaid.consultoriomedico.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    //@Autowired
    //This service injects the Doctor repository, so we can use JpaRepository querys
   private final DoctorRepository doctorRepository;

    //getDoctor method gets a doctor's id sends it to .finById method from JpaRepository and returns a Doctor,
    // it's surrounded with optional because it can be empty or not
    public Optional<Doctor> getDoctor(final int id){return doctorRepository.findById(id);}

    //saveDoctor method gets a doctor's info and saves it in database using .save method from JpaRepository
    public void saveDoctor(Doctor doctor){
        doctorRepository.save(doctor);
    }

    //deleteDoctorById gets a doctor's id, and sends it to .deleteById method from JpaRepository
    public void deleteDoctor(final int id){
        doctorRepository.deleteById(id);
    }


    public Doctor updateDoctorById(Doctor doctorForm, final int id){
        //updateDoctorById gets a doctor's id and looks for it with .findById method from JpaRepository, also gets the request
        Doctor doctorDTO=doctorRepository.findById(id).get();
        //Each chunk of data is got by request, and it's set on the Doctor found with id
        //I omit the id field, so it cannot be changed
        doctorDTO.setName(doctorForm.getName());
        doctorDTO.setSpecialty(doctorForm.getSpecialty());
        doctorDTO.setAddress(doctorForm.getAddress());
        doctorDTO.setPhoneNumber(doctorForm.getPhoneNumber());
        doctorDTO.setEmail(doctorForm.getEmail());
        //Finally all doctor's changes are saved with .save method from JpaRepository
        doctorRepository.save(doctorDTO);

        return doctorDTO;
    }

}
