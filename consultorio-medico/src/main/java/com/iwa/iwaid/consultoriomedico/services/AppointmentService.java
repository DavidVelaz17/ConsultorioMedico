package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.AppointmentDTO;
import com.iwa.iwaid.consultoriomedico.dto.DoctorDTO;
import com.iwa.iwaid.consultoriomedico.dto.PatientDTO;
import com.iwa.iwaid.consultoriomedico.entity.Appointment;
import com.iwa.iwaid.consultoriomedico.entity.Hour;
import com.iwa.iwaid.consultoriomedico.form.AppointmentForm;
import com.iwa.iwaid.consultoriomedico.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {

    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentRepository appointmentRepository;

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

    public AppointmentDTO getAppointmentById(final int id) throws Exception {
        validateIfAppointmentExists(id);
        final Appointment appointment = appointmentRepository.findById(id).get();
        final DoctorDTO doctorDTO = doctorService.getDoctorById(appointment.getDoctorId());
        final PatientDTO patientDTO = patientService.getPatientById(appointment.getPatientId());
        return AppointmentDTO.build(appointment, doctorDTO, patientDTO);
    }

    public AppointmentDTO createAppointment(final AppointmentForm form) throws Exception {
        final Appointment appointment = new Appointment(form);
        validateDoctorAndDateAndHour(
            appointment.getDoctorId(),
            appointment.getDate(),
            appointment.getHour());
        validatePatientAndDateAndHour(
            appointment.getPatientId(),
            appointment.getDate(),
            appointment.getHour());
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
        validateDoctorAndDateAndHour(
            appointment.getDoctorId(),
            appointment.getDate(),
            appointment.getHour());
        validatePatientAndDateAndHour(
            appointment.getPatientId(),
            appointment.getDate(),
            appointment.getHour());
        doctorService.validateIfDoctorExists(appointment.getDoctorId());
        patientService.validateIfPatientExist(appointment.getPatientId());
        appointment.updateAppointment(form);
        appointmentRepository.save(appointment);
        return AppointmentDTO.build(appointment);
    }

    private void validateIfAppointmentExists(int id) throws Exception {
        if (!appointmentRepository.existsById(id)) {
            throw new Exception("The appointment does not exist yet");
        }
    }

    private void validateDoctorAndDateAndHour(
    final Integer doctorId,
    final LocalDate date,
    final Hour hour)
        throws Exception {
        if (appointmentRepository.existsByDoctorIdAndDateAndHour(doctorId,date, hour)) {
            throw new Exception("This date and hour has been taken for this Doctor");
        } else
            log.info("The selected date and hour was available for this Doctor");
    }

    private void validatePatientAndDateAndHour(
    final Integer patientId,
    final LocalDate date,
    final Hour hour)
        throws Exception{
        if(appointmentRepository.existsByPatientIdAndDateAndHour(patientId,date,hour)){
            throw new Exception("This date and hour has been taken for this Patient");
        }else
            log.info("The selected date and hour was available for this Patient");
    }
    private Map<Integer, DoctorDTO> getDoctorsMap(List<Integer> doctorsIds) {
        return doctorService.getDoctorsByIds(doctorsIds);
    }

    private Map<Integer, PatientDTO> getPatientsMap(List<Integer> patientsIds) {
        return patientService.getPatientByIds(patientsIds);
    }
}
