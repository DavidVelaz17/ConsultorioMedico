package com.iwa.iwaid.consultoriomedico.restcontrollers;

import com.iwa.iwaid.consultoriomedico.dto.PatientDTO;
import com.iwa.iwaid.consultoriomedico.form.PatientFilterForm;
import com.iwa.iwaid.consultoriomedico.form.PatientForm;
import com.iwa.iwaid.consultoriomedico.services.PatientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/iwaid/patients/")
@RequiredArgsConstructor
public class PatientController {

    @Autowired
    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        final List<PatientDTO> patients = patientService.findAllPatients();
        return ResponseEntity.ok().body(patients);
    }

    @GetMapping("{id}")
    public ResponseEntity<PatientDTO> getPatientById(
    @PathVariable final int id)
        throws Exception {
        final PatientDTO patient = patientService.getPatientById(id);
        return ResponseEntity.ok().body(patient);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<PatientDTO>> getAllByFilters(
    @RequestBody final PatientFilterForm form) {
        final List<PatientDTO> patientDTOS = patientService.getAllByFilters(form);
        return ResponseEntity.ok().body(patientDTOS);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(
    @RequestBody @Valid final PatientForm form) {
        final PatientDTO patientDTO = patientService.createPatient(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientDTO);
    }

    @PatchMapping("{id}")
    public ResponseEntity<PatientDTO> updatePatient(
    @RequestBody @Valid final PatientForm form,
    @PathVariable final int id)
        throws Exception {
        final PatientDTO patient = patientService.updatePatient(form, id);
        return ResponseEntity.ok().body(patient);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePatient(
    @PathVariable("id") final int id)
        throws Exception {
        patientService.deletePatient(id);
        return ResponseEntity.ok().build();
    }
}
