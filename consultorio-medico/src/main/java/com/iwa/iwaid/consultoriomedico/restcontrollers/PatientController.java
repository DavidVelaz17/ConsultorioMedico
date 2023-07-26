package com.iwa.iwaid.consultoriomedico.restcontrollers;

import com.iwa.iwaid.consultoriomedico.dto.PatientDTO;
import com.iwa.iwaid.consultoriomedico.form.PatientForm;
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
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable int id) throws Exception {
        PatientDTO patient = patientService.getPatientById(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody final PatientForm form) {
        PatientDTO patientDTO = patientService.createPatient(form);
        return new ResponseEntity<>(patientDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody PatientForm patientForm, @PathVariable int id) throws Exception {
        PatientDTO patient = patientService.updatePatient(patientForm, id);
        return new ResponseEntity<>(patient,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) throws Exception {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
