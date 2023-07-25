package com.iwa.iwaid.consultoriomedico.restcontrollers;

import com.iwa.iwaid.consultoriomedico.entity.PatientDTO;
import com.iwa.iwaid.consultoriomedico.entity.PatientForm;
import com.iwa.iwaid.consultoriomedico.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path = "/iwaid/patients/")
public class PatientController {

    @Autowired
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getbyId(@PathVariable int id) {
        Optional<PatientDTO> patient = Optional.ofNullable(patientService.getPatient(id));
        if (patient.isPresent()) {
            return new ResponseEntity<>(patient.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<PatientDTO> savePatient(@RequestBody final PatientForm patientForm) {
        PatientDTO patientDTO = patientService.savePatient(patientForm);
        return new ResponseEntity<>(patientDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody PatientForm patientForm, @PathVariable int id) {
        Optional<PatientDTO> patient1 = Optional.ofNullable(patientService.updatePatient(patientForm, id));
        if (patient1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (patientService.deletePatient(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
