package com.iwa.iwaid.consultoriomedico.restcontrollers;

import com.iwa.iwaid.consultoriomedico.dto.PrescriptionDTO;
import com.iwa.iwaid.consultoriomedico.form.PrescriptionForm;
import com.iwa.iwaid.consultoriomedico.services.PrescriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/iwaid/prescription/")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService service;

    @GetMapping
    public ResponseEntity<List<PrescriptionDTO>> getAllPrescription(){
        final List<PrescriptionDTO> prescriptions = service.getAllPrescriptions();
        return ResponseEntity.ok().body(prescriptions);
    }

    @GetMapping("{prescriptionId}")
    public ResponseEntity<PrescriptionDTO> getPrescriptionById(
    @PathVariable final int prescriptionId)
        throws Exception {
        final PrescriptionDTO prescription = service.findPrescriptionById(prescriptionId);
        return ResponseEntity.ok().body(prescription);
    }

    @PostMapping
    public ResponseEntity<PrescriptionDTO> createPrescription(
    @Valid @RequestBody final PrescriptionForm form)
        throws Exception {
        final PrescriptionDTO prescription = service.createPrescription(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(prescription);
    }

    @PatchMapping("{prescriptionId}")
    public ResponseEntity<PrescriptionDTO> updatePrescription(
    @Valid @RequestBody final PrescriptionForm form,
    @PathVariable final int prescriptionId)
        throws Exception {
        final PrescriptionDTO prescription = service.updatePrescription(form, prescriptionId);
        return ResponseEntity.ok().body(prescription);
    }

    @DeleteMapping("{prescriptionId}")
    public ResponseEntity<Void> deletePrescription(
    @PathVariable final int prescriptionId)
        throws Exception {
        service.deletePrescription(prescriptionId);
        return ResponseEntity.ok().build();
    }
}
