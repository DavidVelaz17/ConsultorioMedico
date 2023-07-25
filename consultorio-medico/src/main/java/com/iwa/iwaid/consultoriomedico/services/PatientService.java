package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.entity.Patient;
import com.iwa.iwaid.consultoriomedico.entity.PatientDTO;
import com.iwa.iwaid.consultoriomedico.entity.PatientForm;
import com.iwa.iwaid.consultoriomedico.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public PatientDTO getPatient(final int id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            return PatientDTO.build(patient.get());
        } else {
            return null;
        }
    }

    public PatientDTO savePatient(final PatientForm patientForm) {
        Patient patient = new Patient(patientForm);
        patientRepository.save(patient);
        return PatientDTO.build(patient);
    }

    public PatientDTO updatePatient(final PatientForm patientForm, final int id) {
        final Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            patient.get().updatePatient(patientForm);
            patientRepository.save(patient.get());
            return PatientDTO.build(patient.get());
        } else {
            return null;
        }
    }

    public boolean deletePatient(int id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        } else
            return false;
    }
}
