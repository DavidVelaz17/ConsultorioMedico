package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.AppointmentDTO;
import com.iwa.iwaid.consultoriomedico.entity.Appointment;
import com.iwa.iwaid.consultoriomedico.form.AppointmentForm;
import com.iwa.iwaid.consultoriomedico.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentDTO getAppointment(final int id) throws Exception {
        validateIfAppointmentExists(id);
        final Appointment appointment = appointmentRepository.findById(id).get();
        return AppointmentDTO.build(appointment);
    }

    public AppointmentDTO saveAppointment(final AppointmentForm form) {
        final Appointment appointment = new Appointment(form);
        appointmentRepository.save(appointment);
        return AppointmentDTO.build(appointment);
    }

    public void deleteAppointment(final int id) throws Exception {
        validateIfAppointmentExists(id);
        appointmentRepository.deleteById(id);
    }

    public AppointmentDTO updateAppointmentById(final AppointmentForm form, final int id) {
        final Appointment appointment = appointmentRepository.findById(id).get();
        appointment.updateAppointment(form);
        appointmentRepository.save(appointment);
        return AppointmentDTO.build(appointment);
    }

    private void validateIfAppointmentExists(int id) throws Exception {
        if (!appointmentRepository.existsById(id)) {
            throw new Exception();
        }
    }
}
