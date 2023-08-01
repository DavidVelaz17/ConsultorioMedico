package com.iwa.iwaid.consultoriomedico.restcontrollers;

import com.iwa.iwaid.consultoriomedico.dto.PatientDTO;
import com.iwa.iwaid.consultoriomedico.form.PatientFilterForm;
import com.iwa.iwaid.consultoriomedico.form.PatientForm;
import com.iwa.iwaid.consultoriomedico.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/iwaid/patients/")
public class PatientController {

    @Autowired
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.findAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable final int id) throws Exception {
        PatientDTO patient = patientService.getPatientById(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<PatientDTO>> getAllByFilters(@RequestBody final PatientFilterForm form) {
        List<PatientDTO> patientDTOS = patientService.getAllByFilters(form);
        return new ResponseEntity<>(patientDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody final PatientForm form) {
        PatientDTO patientDTO = patientService.createPatient(form);
        return new ResponseEntity<>(patientDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody final PatientForm form,
                                                    @PathVariable final int id) throws Exception {
        PatientDTO patient = patientService.updatePatient(form, id);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePatient(@PathVariable("id") final int id) throws Exception {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
