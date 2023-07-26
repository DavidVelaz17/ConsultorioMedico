package com.iwa.iwaid.consultoriomedico.restcontrollers;

import com.iwa.iwaid.consultoriomedico.dto.AppointmentDTO;
import com.iwa.iwaid.consultoriomedico.form.AppointmentForm;
import com.iwa.iwaid.consultoriomedico.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/iwaid/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable("appointmentId") final int appointmentId) throws Exception {
        AppointmentDTO appointmentDTO = appointmentService.getAppointment(appointmentId);
        return ResponseEntity.ok().body(appointmentDTO);
    }

    @PostMapping("/")
    public ResponseEntity saveAppointment(@RequestBody AppointmentForm form) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.saveAppointment(form));
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDTO> deleteAppointment(@PathVariable("appointmentId") final int appointmentId) throws Exception {
        appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDTO> updateAppointmentById(@RequestBody AppointmentForm form, @PathVariable("appointmentId") final int appointmentId) {
        appointmentService.updateAppointmentById(form, appointmentId);
        return ResponseEntity.ok().build();
    }
}
