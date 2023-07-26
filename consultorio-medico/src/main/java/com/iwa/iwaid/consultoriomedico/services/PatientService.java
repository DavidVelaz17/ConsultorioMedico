package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.PatientDTO;
import com.iwa.iwaid.consultoriomedico.entity.Patient;
import com.iwa.iwaid.consultoriomedico.form.PatientForm;
import com.iwa.iwaid.consultoriomedico.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public PatientDTO getPatientById(final int id) throws Exception {
        validateIfPatientExist(id);
        Patient patient = patientRepository.findById(id).orElseThrow();
        return PatientDTO.build(patient);
    }

    public PatientDTO createPatient(final PatientForm patientForm) {
        Patient patient = new Patient(patientForm);
        patientRepository.save(patient);
        return PatientDTO.build(patient);
    }

    public PatientDTO updatePatient(final PatientForm patientForm, final int id) throws Exception {
        validateIfPatientExist(id);
        final Patient patient = patientRepository.findById(id).orElseThrow();
        patient.updatePatient(patientForm);
        patientRepository.save(patient);
        return PatientDTO.build(patient);
    }

    public void deletePatient(int id) throws Exception {
        validateIfPatientExist(id);
        patientRepository.deleteById(id);
    }

    public void validateIfPatientExist(int id) throws Exception {
        if (!patientRepository.existsById(id)) {
            throw new Exception("Patient not found");
        }
    }
}
