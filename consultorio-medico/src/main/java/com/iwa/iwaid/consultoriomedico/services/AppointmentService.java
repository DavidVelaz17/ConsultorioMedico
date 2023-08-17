package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.AppointmentDTO;
import com.iwa.iwaid.consultoriomedico.dto.DoctorDTO;
import com.iwa.iwaid.consultoriomedico.dto.PatientDTO;
import com.iwa.iwaid.consultoriomedico.entity.Appointment;
import com.iwa.iwaid.consultoriomedico.entity.Doctor;
import com.iwa.iwaid.consultoriomedico.entity.Hour;
import com.iwa.iwaid.consultoriomedico.form.AppointmentForm;
import com.iwa.iwaid.consultoriomedico.form.DoctorForm;
import com.iwa.iwaid.consultoriomedico.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentRepository appointmentRepository;
    private final ResourceBundle messages =
            ResourceBundle.getBundle("ValidationMessages");

    public List<AppointmentDTO> getAll() {
        final List<Appointment> appointments = appointmentRepository.findAll();
        final Map<Integer, DoctorDTO> doctorDTOMap =
                getDoctorsMap(appointments.stream().map(Appointment::getDoctorId).toList());
        final Map<Integer, PatientDTO> patientDTOMap =
                getPatientsMap(appointments.stream().map(Appointment::getPatientId).toList());
        return appointments
                .stream()
                .map(appointment -> AppointmentDTO
                    .build(appointment,
                       doctorDTOMap
                       .get(appointment.getDoctorId()),
                       patientDTOMap
                       .get(appointment.getPatientId())))
                .toList();
    }

    public AppointmentDTO getAppointmentById(final int appointmentId) throws Exception {
        validateIfAppointmentExists(appointmentId);
        final Appointment appointment =
                appointmentRepository.findById(appointmentId).get();
        final DoctorDTO doctorDTO =
                doctorService.getDoctorById(appointment.getDoctorId());
        final PatientDTO patientDTO =
                patientService.getPatientById(appointment.getPatientId());
        return AppointmentDTO.build(appointment, doctorDTO, patientDTO);
    }

    public AppointmentDTO createAppointment(final AppointmentForm form) throws Exception {
        final LocalDate date = form.getDate();
        final Hour hour = form.getHour();
        final int doctorId = form.getDoctorId();
        final int patientId = form.getPatientId();
        doctorService.validateIfDoctorExists(doctorId);
        patientService.validateIfPatientExist(patientId);
        validateDoctorAvailability(
           doctorId,
           date,
           hour);
        validatePatientAvailability(
           patientId,
           date,
           hour);
        final Appointment appointment = new Appointment(form);
        doctorService.validateIfDoctorExists(appointment.getDoctorId());
        patientService.validateIfPatientExist(appointment.getPatientId());
        appointmentRepository.save(appointment);
        return AppointmentDTO.build(appointment);
    }

    public void deleteAppointment(final int appointmentId) throws Exception {
        validateIfAppointmentExists(appointmentId);
        appointmentRepository.deleteById(appointmentId);
    }

    public AppointmentDTO updateAppointmentById(
    final AppointmentForm form,
    final int appointmentId)
        throws Exception {
        final LocalDate date = form.getDate();
        final Hour hour = form.getHour();
        final int doctorId = form.getDoctorId();
        final int patientId = form.getPatientId();
        doctorService.validateIfDoctorExists(doctorId);
        patientService.validateIfPatientExist(patientId);
        validateIfAppointmentExists(appointmentId);
        validateDuplicatedAppointmentByDoctor(
           appointmentId,
           doctorId,
           date,
           hour);
        validateDuplicatedAppointmentByPatient(
           appointmentId,
           patientId,
           date,
           hour);
        final Appointment appointment = appointmentRepository.findById(appointmentId).get();
        doctorService.validateIfDoctorExists(appointment.getDoctorId());
        patientService.validateIfPatientExist(appointment.getPatientId());
        appointment.updateAppointment(form);
        appointmentRepository.save(appointment);
        return AppointmentDTO.build(appointment);
    }

    private void validateIfAppointmentExists(final int appointmentId) throws Exception {
        if (!appointmentRepository.existsById(appointmentId)) {
            throw new Exception(
                    messages.getString("not.found")
                    + " -Appointment:"
                    + appointmentId);
        }
    }

    private void validateDoctorAvailability(
    final Integer doctorId,
    final LocalDate date,
    final Hour hour)
        throws Exception {
        if (appointmentRepository.existsByDoctorIdAndDateAndHour(doctorId, date, hour))
            throw new Exception(
                    messages.getString("date.taken")
                    + " Doctor:"
                    + doctorId);
    }

    private void validatePatientAvailability(
    final Integer patientId,
    final LocalDate date,
    final Hour hour)
        throws Exception {
        if (appointmentRepository.existsByPatientIdAndDateAndHour(patientId, date, hour))
            throw new Exception(
                    messages.getString("date.taken")
                    + " Patient:"
                    + patientId);
    }

    private void validateDuplicatedAppointmentByDoctor(
    final Integer appointmentId,
    final Integer doctorId,
    final LocalDate date,
    final Hour hour)
        throws Exception {
        if (appointmentRepository.existsByDoctorIdAndDateAndHourAndIdNot(
                doctorId,
                date,
                hour,
                appointmentId))
            throw new Exception(messages.getString("date.taken"));
    }

    private void validateDuplicatedAppointmentByPatient(
    final Integer appointmentId,
    final Integer doctorId,
    final LocalDate date,
    final Hour hour)
        throws Exception {
        if (appointmentRepository.existsByPatientIdAndDateAndHourAndIdNot(
                doctorId,
                date,
                hour,
                appointmentId))
            throw new Exception(messages.getString("date.taken"));
    }

    private Map<Integer, DoctorDTO> getDoctorsMap(final List<Integer> doctorsIds) {
        return doctorService.getDoctorsByIds(doctorsIds);
    }

    private Map<Integer, PatientDTO> getPatientsMap(final List<Integer> patientsIds) {
        return patientService.getPatientByIds(patientsIds);
    }
}
