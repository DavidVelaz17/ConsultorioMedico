package com.iwa.iwaid.consultoriomedico.restcontrollers;

import com.iwa.iwaid.consultoriomedico.dto.PrescriptionDTO;
import com.iwa.iwaid.consultoriomedico.form.PrescriptionForm;
import com.iwa.iwaid.consultoriomedico.services.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/iwaid/prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionService service;

    public PrescriptionController(PrescriptionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PrescriptionDTO>> getAllPrescription() throws Exception {
        return new ResponseEntity<>(service.getAllPrescriptions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDTO> getPrescriptionById(@PathVariable final int id)
            throws Exception {
        PrescriptionDTO prescription = service.findPrescriptionById(id);
        return new ResponseEntity<>(prescription, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PrescriptionDTO> createPrescription(@Valid @RequestBody final PrescriptionForm form)
            throws Exception {
        PrescriptionDTO prescription = service.createPrescription(form);
        return new ResponseEntity<>(prescription, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PrescriptionDTO> updatePrescription(@Valid @RequestBody final PrescriptionForm form,
                                                              @PathVariable final int id) throws Exception {
        PrescriptionDTO prescription = service.updatePrescription(form, id);
        return new ResponseEntity<>(prescription, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable final int id) throws Exception {
        service.deletePrescription(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
