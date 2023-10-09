package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.DoctorDTO;
import com.iwa.iwaid.consultoriomedico.dto.PatientDTO;
import com.iwa.iwaid.consultoriomedico.dto.PrescriptionDTO;
import com.iwa.iwaid.consultoriomedico.entity.Prescription;
import com.iwa.iwaid.consultoriomedico.form.PrescriptionForm;
import com.iwa.iwaid.consultoriomedico.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:ValidationMessages.properties")
public class PrescriptionService {

    private final PrescriptionRepository repository;
    private final PatientService patientService;
    private final DoctorService doctorService;

    @Value("${not.found}")
    private String notFound;

    public List<PrescriptionDTO> getAllPrescriptions() {
        final List<Prescription> prescriptions = repository.findAll();
        final Map<Integer, PatientDTO> patientDTOMap =
                getPatientsMap(prescriptions.stream().map(Prescription::getPatientId).toList());
        final Map<Integer, DoctorDTO> doctorDTOMap =
                getDoctorsMap(prescriptions.stream().map(Prescription::getDoctorId).toList());
        return prescriptions
                .stream()
                .map(prescription -> PrescriptionDTO
                        .build(prescription,
                                patientDTOMap
                                        .get(prescription.getPatientId()),
                                doctorDTOMap
                                        .get(prescription.getDoctorId())
                        ))
                .toList();
    }

    public PrescriptionDTO findPrescriptionById(final int prescriptionId) throws Exception {
        validateIfPrescriptionExist(prescriptionId);
        final Prescription prescription = repository.findById(prescriptionId).orElseThrow();
        final PatientDTO patientDTO = patientService.getPatientById(prescription.getPatientId());
        final DoctorDTO doctorDTO = doctorService.getDoctorById(prescription.getDoctorId());
        return PrescriptionDTO.build(prescription, patientDTO, doctorDTO);
    }

    public PrescriptionDTO createPrescription(final PrescriptionForm form) throws Exception {
        patientService.validateIfPatientExist(form.getPatientId());
        doctorService.validateIfDoctorExists(form.getDoctorId());
        final Prescription prescription = new Prescription(form);
        repository.save(prescription);
        return PrescriptionDTO.build(prescription);
    }

    public PrescriptionDTO updatePrescription(final PrescriptionForm form, final int prescriptionId)
            throws Exception {
        validateIfPrescriptionExist(prescriptionId);
        patientService.validateIfPatientExist(form.getPatientId());
        doctorService.validateIfDoctorExists(form.getDoctorId());
        final Prescription prescription = repository.findById(prescriptionId).orElseThrow();
        prescription.updatePrescription(form);
        repository.save(prescription);
        return PrescriptionDTO.build(prescription);
    }

    public void deletePrescription(final int prescriptionId) throws Exception {
        validateIfPrescriptionExist(prescriptionId);
        repository.deleteById(prescriptionId);
    }

    public void validateIfPrescriptionExist(final int prescriptionId) throws Exception {
        if (!repository.existsById(prescriptionId)) {
            throw new Exception(notFound);
        }
    }

    private Map<Integer, DoctorDTO> getDoctorsMap(final List<Integer> doctorsIds) {
        return doctorService.getDoctorsByIds(doctorsIds);
    }

    private Map<Integer, PatientDTO> getPatientsMap(final List<Integer> patientsIds) {
        return patientService.getPatientByIds(patientsIds);
    }

}