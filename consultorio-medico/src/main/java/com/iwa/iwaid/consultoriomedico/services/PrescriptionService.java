package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.DoctorDTO;
import com.iwa.iwaid.consultoriomedico.dto.PatientDTO;
import com.iwa.iwaid.consultoriomedico.dto.PrescriptionDTO;
import com.iwa.iwaid.consultoriomedico.entity.Prescription;
import com.iwa.iwaid.consultoriomedico.form.PrescriptionForm;
import com.iwa.iwaid.consultoriomedico.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository repository;
    private final PatientService patientService;
    private final DoctorService doctorService;

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

    public PrescriptionDTO findPrescriptionById(final int id) throws Exception {
        validateIfPrescriptionExist(id);
        final Prescription prescription = repository.findById(id).orElseThrow();
        final PatientDTO patientDTO = patientService.getPatientById(prescription.getPatientId());
        final DoctorDTO doctorDTO = doctorService.getDoctorById(prescription.getDoctorId());
        return PrescriptionDTO.build(prescription, patientDTO, doctorDTO);
    }

    public PrescriptionDTO createPrescription(final PrescriptionForm form) throws Exception {
        final Prescription prescription = new Prescription(form);
        patientService.validateIfPatientExist(form.getPatientId());
        doctorService.validateIfDoctorExists(form.getDoctorId());
        repository.save(prescription);
        return PrescriptionDTO.build(prescription);
    }

    public PrescriptionDTO updatePrescription(final PrescriptionForm form, final int id)
            throws Exception {
        validateIfPrescriptionExist(id);
        final Prescription prescription = repository.findById(id).orElseThrow();
        patientService.validateIfPatientExist(form.getPatientId());
        doctorService.validateIfDoctorExists(form.getDoctorId());
        prescription.updatePrescription(form);
        repository.save(prescription);
        return PrescriptionDTO.build(prescription);
    }

    public void deletePrescription(final int id) throws Exception {
        validateIfPrescriptionExist(id);
        repository.deleteById(id);
    }

    public void validateIfPrescriptionExist(final int id) throws Exception {
        if (!repository.existsById(id)) {
            throw new Exception("Prescription not found with ID: " + id);
        }
    }

    private Map<Integer, DoctorDTO> getDoctorsMap(List<Integer> doctorsIds) {
        return doctorService.getDoctorsByIds(doctorsIds);
    }

    private Map<Integer, PatientDTO> getPatientsMap(List<Integer> patientsIds) {
        return patientService.getPatientByIds(patientsIds);
    }

}