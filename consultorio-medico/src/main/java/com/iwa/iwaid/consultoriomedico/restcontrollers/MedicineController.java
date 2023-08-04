package com.iwa.iwaid.consultoriomedico.restcontrollers;

import com.iwa.iwaid.consultoriomedico.dto.MedicineDTO;
import com.iwa.iwaid.consultoriomedico.form.MedicineFiltersForm;
import com.iwa.iwaid.consultoriomedico.form.MedicineForm;
import com.iwa.iwaid.consultoriomedico.services.MedicineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/iwaid/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @GetMapping
    public List<MedicineDTO> getAllDoctorsByFilters(@RequestBody @Valid @ModelAttribute MedicineFiltersForm form) {
        List<MedicineDTO> medicines = medicineService.getAllByFilters(form);
        return medicines;
    }

    @GetMapping("/{medicineId}")
    public ResponseEntity<MedicineDTO> getMedicineById(@PathVariable("medicineId") final int medicineId) throws Exception {
        MedicineDTO medicineDTO = medicineService.getMedicineById(medicineId);
        return ResponseEntity.ok().body(medicineDTO);
    }

    @PostMapping
    public ResponseEntity createMedicine(@RequestBody @Valid MedicineForm form) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicineService.createMedicine(form));
    }

    @DeleteMapping("/{medicineId}")
    public ResponseEntity<MedicineDTO> deleteMedicine(@PathVariable("medicineId") final int medicineId) throws Exception {
        medicineService.deleteMedicine(medicineId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{medicineId}")
    public ResponseEntity<MedicineDTO> updateMedicine(@RequestBody @Valid MedicineForm form, @PathVariable("medicineId") final int medicineId) throws Exception {
        medicineService.updateMedicine(form, medicineId);
        return ResponseEntity.ok().build();
    }
}
