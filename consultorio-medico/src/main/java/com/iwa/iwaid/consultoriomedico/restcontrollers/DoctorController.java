package com.iwa.iwaid.consultoriomedico.restcontrollers;

import com.iwa.iwaid.consultoriomedico.entity.Doctors;
import com.iwa.iwaid.consultoriomedico.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/iwaid/doctors")//Path where the api can be found
public class DoctorController {

    @Autowired
    //This controller injects the DoctorService
    DoctorService doctorService;

    @GetMapping("/{doctorId}")
    //The path to get a doctor's info is /{id}, the {id} must be replaced by an actual doctor's id
    public Optional<Doctors> getDoctor(@PathVariable("doctorId") int doctorId){
        return doctorService.getDoctor(doctorId);//it sends the doctor's id to DoctorService .getDoctor method and returns all doctor's info
    }

    @PostMapping("/")
    //The path to save a doctor's info is /, in the body goes the info as JSON


    public ResponseEntity save(@RequestBody Doctors doctors){
            doctorService.saveDoctor(doctors);    //it sends the doctor's info to DoctorService with .saveDoctor method
            return new ResponseEntity<>("Saved doctor " + doctors.getId(), HttpStatus.CREATED);  //Returns a ResponseEntity with the doctor's id and the status code of 201 for created
    }

    @DeleteMapping("/{doctorId}")
    //The path to delete doctor's info is /{id}, the {id} must be replaced by an actual doctor's id
    public ResponseEntity delete(@PathVariable("doctorId") int doctorId){
            doctorService.deleteDoctor(doctorId);//it sends the doctor's id to DoctorService with .deleteDoctor method
            return new ResponseEntity<>(HttpStatus.OK);   //Returns a ResponseEntity with the status code of 200 for OK
    }

    @PatchMapping("/{doctorId}")
    //The path to update a doctor's info is /{id}, the {id} must be replaced by an actual doctor's id
    //also in the body goes the info to change as JSON
    public ResponseEntity updateById(@RequestBody Doctors doctors, @PathVariable("doctorId")int doctorId){
            Doctors doctor=doctorService.updateDoctorById(doctors,doctorId);//it sends the doctor's id and info to DoctorService .updateDoctorById method
            return new ResponseEntity<>(HttpStatus.OK);//Returns a ResponseEntity with the status code of 200 for OK
    }
}
