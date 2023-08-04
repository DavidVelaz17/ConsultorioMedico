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
        final Map<Integer, DoctorDTO> doctorDTOMap = getDoctorsMap(appointments.stream().map(Appointment::getDoctorId).toList());
        final Map<Integer, PatientDTO> patientDTOMap = getPatientsMap(appointments.stream().map(Appointment::getPatientId).toList());
        return appointments.stream().map(appointment -> AppointmentDTO.build(appointment, doctorDTOMap.get(appointment.getDoctorId()), patientDTOMap.get(appointment.getPatientId()))).toList();
    }

    public AppointmentDTO getAppointmentById(final int id) throws Exception {
        validateIfAppointmentExists(id);
        final Appointment appointment = appointmentRepository.findById(id).get();
        final DoctorDTO doctorDTO = doctorService.getDoctorById(appointment.getDoctorId());
        final PatientDTO patientDTO = patientService.getPatientById(appointment.getPatientId());
        return AppointmentDTO.build(appointment, doctorDTO, patientDTO);
    }

    public AppointmentDTO saveAppointment(final AppointmentForm form) throws Exception {
        final Appointment appointment = new Appointment(form);
        doctorService.validateIfDoctorExists(appointment.getDoctorId());
        patientService.validateIfPatientExist(appointment.getPatientId());
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
        doctorService.validateIfDoctorExists(appointment.getDoctorId());
        patientService.validateIfPatientExist(appointment.getPatientId());
        appointment.updateAppointment(form);
        appointmentRepository.save(appointment);
        return AppointmentDTO.build(appointment);
    }

    private void validateIfAppointmentExists(int id) throws Exception {
        if (!appointmentRepository.existsById(id)) {
            throw new Exception();
        }
    }

    private Map<Integer, DoctorDTO> getDoctorsMap(List<Integer> doctorsIds) {
        final Map<Integer, DoctorDTO> doctorDTOMap = doctorService.getDoctorsByIds(doctorsIds);
        return doctorDTOMap;
    }

    private Map<Integer, PatientDTO> getPatientsMap(List<Integer> patientsIds) {
        final Map<Integer, PatientDTO> patientDTOMap = patientService.getPatientByIds(patientsIds);
        return patientDTOMap;
    }
}
