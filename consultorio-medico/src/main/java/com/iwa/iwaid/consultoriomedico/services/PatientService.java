package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.PatientDTO;
import com.iwa.iwaid.consultoriomedico.entity.Patient;
import com.iwa.iwaid.consultoriomedico.form.PatientFilterForm;
import com.iwa.iwaid.consultoriomedico.form.PatientForm;
import com.iwa.iwaid.consultoriomedico.repository.PatientRepository;
import com.iwa.iwaid.consultoriomedico.repository.PatientSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<PatientDTO> findAllPatients() {
        List<Patient> patientPage = patientRepository.findAll();
        return patientPage.stream().map(PatientDTO::build).toList();
    }

    public PatientDTO getPatientById(final int id) throws Exception {
        validateIfPatientExist(id);
        Patient patient = patientRepository.findById(id).orElseThrow();
        return PatientDTO.build(patient);
    }

    public List<PatientDTO> getAllByFilters(final PatientFilterForm form) {
        List<Patient> patients = patientRepository.findAll(PatientSpecs.getAllByFilters(form));
        return patients.stream().map(PatientDTO::build).toList();
    }

    public PatientDTO createPatient(final PatientForm form) {
        Patient patient = new Patient(form);
        patientRepository.save(patient);
        return PatientDTO.build(patient);
    }

    public PatientDTO updatePatient(final PatientForm form, final int id) throws Exception {
        validateIfPatientExist(id);
        final Patient patient = patientRepository.findById(id).orElseThrow();
        patient.updatePatient(form);
        patientRepository.save(patient);
        return PatientDTO.build(patient);
    }

    public void deletePatient(final int id) throws Exception {
        validateIfPatientExist(id);
        patientRepository.deleteById(id);
    }

    public void validateIfPatientExist(final int id) throws Exception {
        if (!patientRepository.existsById(id)) {
            throw new Exception("Patient not found");
        }
    }
}
