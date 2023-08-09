package com.iwa.iwaid.consultoriomedico.restcontrollers;

import com.iwa.iwaid.consultoriomedico.dto.MedicalHistoryDTO;
import com.iwa.iwaid.consultoriomedico.form.MedicalHistoryForm;
import com.iwa.iwaid.consultoriomedico.services.MedicalHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/iwaid/medicalhistory/")
public class MedicalHistoryController {

    private final MedicalHistoryService service;

    @GetMapping
    public ResponseEntity<List<MedicalHistoryDTO>> getAllMedicalHistory() {
        List<MedicalHistoryDTO> history = service.getAllMedicalHistorys();
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @GetMapping("{medicalHistoryId}")
    public ResponseEntity<MedicalHistoryDTO> getMedicalHistoryById(
            @PathVariable final int medicalHistoryId)
            throws Exception {
        MedicalHistoryDTO history = service.getMedicalHistorybyId(medicalHistoryId);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MedicalHistoryDTO> createMedicalHistory(
            @RequestBody @Valid final MedicalHistoryForm form)
            throws Exception {
        MedicalHistoryDTO history = service.createMedicalHistory(form);
        return new ResponseEntity<>(history, HttpStatus.CREATED);
    }

    @PatchMapping("{medicalHistoryId}")
    public ResponseEntity<MedicalHistoryDTO> updateMedicalHistory(
            @RequestBody @Valid final MedicalHistoryForm form,
            @PathVariable final int medicalHistoryId) throws Exception {
        MedicalHistoryDTO history = service.updateMedicalHistory(form, medicalHistoryId);
        return new ResponseEntity<>(history, HttpStatus.CREATED);
    }

    @DeleteMapping("{medicalHistoryId}")
    public ResponseEntity<Void> deleteMedicalHistory(@PathVariable final int medicalHistoryId)
            throws Exception {
        service.deleteMedicalHistory(medicalHistoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
