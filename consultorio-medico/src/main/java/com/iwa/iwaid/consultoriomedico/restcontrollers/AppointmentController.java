package com.iwa.iwaid.consultoriomedico.restcontrollers;

import com.iwa.iwaid.consultoriomedico.dto.AppointmentDTO;
import com.iwa.iwaid.consultoriomedico.form.AppointmentForm;
import com.iwa.iwaid.consultoriomedico.services.AppointmentService;
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

import java.util.List;

@RestController
@RequestMapping(path = "/iwaid/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentService.getAll();
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable("appointmentId") final int appointmentId)
            throws Exception {
        final AppointmentDTO appointmentDTO = appointmentService.getAppointmentById(appointmentId);
        return ResponseEntity.ok().body(appointmentDTO);
    }

    @PostMapping
    public ResponseEntity createAppointment(@RequestBody @Valid AppointmentForm form) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createAppointment(form));
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable("appointmentId") final int appointmentId)
            throws Exception {
        appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDTO> updateAppointmentById(@RequestBody @Valid AppointmentForm form,
                                                                @PathVariable("appointmentId") final int appointmentId)
                                                                throws Exception {
        appointmentService.updateAppointmentById(form, appointmentId);
        return ResponseEntity.ok().build();
    }
}
