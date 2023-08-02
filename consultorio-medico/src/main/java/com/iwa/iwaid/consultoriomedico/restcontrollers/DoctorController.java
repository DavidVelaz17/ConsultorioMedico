package com.iwa.iwaid.consultoriomedico.restcontrollers;

import com.iwa.iwaid.consultoriomedico.dto.DoctorDTO;
import com.iwa.iwaid.consultoriomedico.form.DoctorFiltersForm;
import com.iwa.iwaid.consultoriomedico.form.DoctorForm;
import com.iwa.iwaid.consultoriomedico.services.DoctorService;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping(path = "/iwaid/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/")
    public List<DoctorDTO> getAllDoctorsByFilters(@RequestBody @ModelAttribute DoctorFiltersForm form) {
        List<DoctorDTO> doctors = doctorService.getAllByFilters(form);
        return doctors;
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable("doctorId") final int doctorId) throws Exception {
        DoctorDTO doctorDTO = doctorService.getDoctorById(doctorId);
        return ResponseEntity.ok().body(doctorDTO);
    }

    @PostMapping("/")
    public ResponseEntity saveDoctor(@RequestBody @Valid DoctorForm form) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctor(form));
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<DoctorDTO> deleteDoctor(@PathVariable("doctorId") final int doctorId) throws Exception {
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{doctorId}")
    public ResponseEntity<DoctorDTO> updateDoctorById(@RequestBody @Valid DoctorForm form, @PathVariable("doctorId") final int doctorId) throws Exception {
        doctorService.updateDoctor(form, doctorId);
        return ResponseEntity.ok().build();
    }
}
