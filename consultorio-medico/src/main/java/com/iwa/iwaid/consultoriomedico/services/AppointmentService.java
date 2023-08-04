package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.AppointmentDTO;
import com.iwa.iwaid.consultoriomedico.dto.DoctorDTO;
import com.iwa.iwaid.consultoriomedico.dto.PatientDTO;
import com.iwa.iwaid.consultoriomedico.entity.Appointment;
import com.iwa.iwaid.consultoriomedico.form.AppointmentForm;
import com.iwa.iwaid.consultoriomedico.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentRepository appointmentRepository;

    public List<AppointmentDTO> getAll() {
        final List<Appointment> appointments = appointmentRepository.findAll();

        final List<Integer> doctorIds = appointments.stream().map(Appointment::getDoctorId).toList();
        final Map<Integer, DoctorDTO> doctorDTOMap = doctorService.getDoctorsByIds(doctorIds);

        final List<Integer> patientIds = appointments.stream().map(Appointment::getPatientId).toList();
        final Map<Integer, PatientDTO> patientDTOMap = patientService.getPatientByIds(patientIds);

        return appointments.stream().map(appointment -> AppointmentDTO.build(appointment, doctorDTOMap.get(appointment.getDoctorId()), patientDTOMap.get(appointment.getPatientId()))).toList();
    }

    public AppointmentDTO getAppointmentById(final int id) throws Exception {
        validateIfAppointmentExists(id);
        final Appointment appointment = appointmentRepository.findById(id).get();

        final Integer doctorId = appointment.getDoctorId();
        final DoctorDTO doctorDTO= doctorService.getDoctorById(doctorId);

        final Integer patientId= appointment.getPatientId();
        final PatientDTO patientDTO=patientService.getPatientById(patientId);

        return AppointmentDTO.build(appointment,doctorDTO,patientDTO);
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

    public AppointmentDTO updateAppointmentById(final AppointmentForm form, final int id) throws Exception {
        validateIfAppointmentExists(id);
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
