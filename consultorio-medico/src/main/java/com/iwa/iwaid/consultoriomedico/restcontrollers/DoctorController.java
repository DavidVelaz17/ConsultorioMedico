package com.iwa.iwaid.consultoriomedico.restcontrollers;

import com.iwa.iwaid.consultoriomedico.entity.Doctor;
import com.iwa.iwaid.consultoriomedico.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.Optional;

@RestController
@RequestMapping(path="/iwaid/doctors")//Path where the api can be found
@RequiredArgsConstructor
public class DoctorController {

    @Autowired
    //This controller injects the DoctorService
    private final DoctorService doctorService;

    @GetMapping("/{doctorId}")
    //The path to get a doctor's info is /{id}, the {id} must be replaced by an actual doctor's id
    public ResponseEntity getDoctorById(@PathVariable("doctorId") final int doctorId){
        Optional<Doctor> doctor=doctorService.getDoctor(doctorId);
        if (doctor.isPresent()){
            return new ResponseEntity(doctor.get(),HttpStatus.FOUND);
        }else{
            return ResponseEntity.notFound().build();
        }
        //return doctorService.getDoctor(doctorId);//it sends the doctor's id to DoctorService .getDoctor method and returns all doctor's info
    }

    @PostMapping("/")
    //The path to save a doctor's info is /, in the body goes the info as JSON
    public ResponseEntity save(@RequestBody Doctor doctor){
            doctorService.saveDoctor(doctor);    //it sends the doctor's info to DoctorService with .saveDoctor method
            return new ResponseEntity<>("Saved doctor " + doctor.getId(), HttpStatus.CREATED);  //Returns a ResponseEntity with the doctor's id and the status code of 201 for created
           // return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.saveDoctor(doctors));
    }

    @DeleteMapping("/{doctorId}")
    //The path to delete doctor's info is /{id}, the {id} must be replaced by an actual doctor's id
    public ResponseEntity delete(@PathVariable("doctorId") final int doctorId){
        Optional<Doctor> doctor=doctorService.getDoctor(doctorId);
        if (doctor.isPresent()) {
            doctorService.deleteDoctor(doctorId);//it sends the doctor's id to DoctorService with .deleteDoctor method
            return ResponseEntity.ok().build();  //Returns a ResponseEntity with the status code of 200 for OK
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{doctorId}")
    //The path to update a doctor's info is /{id}, the {id} must be replaced by an actual doctor's id
    //also in the body goes the info to change as JSON
    public ResponseEntity updateById(@RequestBody Doctor doctor, @PathVariable("doctorId") final int doctorId){
        Optional<Doctor> doctor1=doctorService.getDoctor(doctorId);
        if (doctor1.isPresent()) {
            Doctor doctorUpdated = doctorService.updateDoctorById(doctor, doctorId);//it sends the doctor's id and info to DoctorService .updateDoctorById method
            return new ResponseEntity<>(HttpStatus.OK);//Returns a ResponseEntity with the status code of 200 for OK
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
