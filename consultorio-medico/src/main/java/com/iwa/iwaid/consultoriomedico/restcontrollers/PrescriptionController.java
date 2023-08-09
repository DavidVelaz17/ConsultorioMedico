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
    public ResponseEntity<List<PrescriptionDTO>> getAllPrescription() throws Exception {
        return new ResponseEntity<>(service.getAllPrescriptions(), HttpStatus.OK);
    }

    @GetMapping("{prescriptionId}")
    public ResponseEntity<PrescriptionDTO> getPrescriptionById(@PathVariable final int prescriptionId)
            throws Exception {
        PrescriptionDTO prescription = service.findPrescriptionById(prescriptionId);
        return new ResponseEntity<>(prescription, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PrescriptionDTO> createPrescription(@Valid @RequestBody final PrescriptionForm form)
            throws Exception {
        PrescriptionDTO prescription = service.createPrescription(form);
        return new ResponseEntity<>(prescription, HttpStatus.CREATED);
    }

    @PatchMapping("{prescriptionId}")
    public ResponseEntity<PrescriptionDTO> updatePrescription(@Valid @RequestBody final PrescriptionForm form,
                                                              @PathVariable final int prescriptionId)
            throws Exception {
        PrescriptionDTO prescription = service.updatePrescription(form, prescriptionId);
        return new ResponseEntity<>(prescription, HttpStatus.OK);
    }

    @DeleteMapping("{prescriptionId}")
    public ResponseEntity<Void> deletePrescription(@PathVariable final int prescriptionId)
            throws Exception {
        service.deletePrescription(prescriptionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
