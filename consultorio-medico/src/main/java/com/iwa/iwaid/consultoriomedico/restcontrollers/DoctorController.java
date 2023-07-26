package com.iwa.iwaid.consultoriomedico.restcontrollers;

import com.iwa.iwaid.consultoriomedico.dto.DoctorDTO;
import com.iwa.iwaid.consultoriomedico.form.DoctorForm;
import com.iwa.iwaid.consultoriomedico.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
@RequestMapping(path = "/iwaid/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable("doctorId") final int doctorId) throws Exception {
        DoctorDTO doctorDTO = doctorService.getDoctor(doctorId);
        return ResponseEntity.ok().body(doctorDTO);
    }

    @PostMapping("/")
    public ResponseEntity saveDoctor(@RequestBody DoctorForm form) {
        DoctorDTO doctorDTO = doctorService.saveDoctor(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.saveDoctor(form));
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<DoctorDTO> deleteDoctor(@PathVariable("doctorId") final int doctorId) throws Exception {
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{doctorId}")
    public ResponseEntity<DoctorDTO> updateDoctorById(@RequestBody DoctorForm form, @PathVariable("doctorId") final int doctorId) throws Exception {
        doctorService.updateDoctorById(form, doctorId);
        return ResponseEntity.ok().build();
    }
}
