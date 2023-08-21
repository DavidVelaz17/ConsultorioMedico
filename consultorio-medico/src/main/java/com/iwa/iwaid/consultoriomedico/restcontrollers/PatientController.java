package com.iwa.iwaid.consultoriomedico.restcontrollers;

import com.iwa.iwaid.consultoriomedico.dto.PatientDTO;
import com.iwa.iwaid.consultoriomedico.form.PatientFilterForm;
import com.iwa.iwaid.consultoriomedico.form.PatientForm;
import com.iwa.iwaid.consultoriomedico.services.PatientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@RestController
@RequestMapping(path = "/iwaid/patients/")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllByFilters(
            @RequestBody @ModelAttribute final PatientFilterForm form) {
        final List<PatientDTO> patientDTOS = patientService.getAllByFilters(form);
        return ResponseEntity.ok().body(patientDTOS);
    }

    @GetMapping("{patientId}")
    public ResponseEntity<PatientDTO> getPatientById(
    @PathVariable final int patientId)
        throws Exception {
        final PatientDTO patient = patientService.getPatientById(patientId);
        return ResponseEntity.ok().body(patient);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(
    @RequestBody @Valid final PatientForm form) {
        final PatientDTO patientDTO = patientService.createPatient(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientDTO);
    }

    @PatchMapping("{patientId}")
    public ResponseEntity<PatientDTO> updatePatient(
    @RequestBody @Valid final PatientForm form,
    @PathVariable final int patientId)
        throws Exception {
        final PatientDTO patient = patientService.updatePatient(form, patientId);
        return ResponseEntity.ok().body(patient);
    }

    @DeleteMapping("{patientId}")
    public ResponseEntity<Void> deletePatient(
    @PathVariable("patientId") final int patientId)
        throws Exception {
        patientService.deletePatient(patientId);
        return ResponseEntity.ok().build();
    }
}
