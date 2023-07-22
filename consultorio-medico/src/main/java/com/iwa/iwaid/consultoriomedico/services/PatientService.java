package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.entity.Patient;
import com.iwa.iwaid.consultoriomedico.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PatientService {

    /** The Autowired annotation is used here to automatically inject the PatientRepository dependency.
     * This means that Spring will find a bean of type PatientRepository and assign it to this field when
     * the PatientService is created.
     */
    @Autowired
    private PatientRepository patientRepository;

    /** The getPatient() method retrieves a specific patient by their ID.
     * It calls the findById method from PatientRepository, which returns an Optional
     * that may contain the Patient if one with the given ID exists.
     */
    public Optional<Patient> getPatient(int id){
        return patientRepository.findById(id);
    }

    /** The savePatient() method is responsible for saving a new patient to the database.
     * It achieves this by calling the save method from the PatientRepository class.
     * The savePatient() method then retrieves the ID of the saved Patient and returns it as the result.
     */
    public int savePatient(Patient patient){
        Patient savePatient = patientRepository.save(patient);
        return savePatient.getId();
    }

    /** The updatePatient() method updates an existing patient in the database.
     * It first retrieves the existing patient by their ID. If the patient exists,
     * it updates the patient's fields with the values from the Patient object passed as a parameter.
     * It then calls the savePatient method to save the updated patient to the database.
     * If the update is successful, it returns an Optional containing the updated patient.
     * If the patient does not exist, it returns an empty Optional.
     */
    public Optional<Patient> updatePatient(Patient patient, int id){
        Optional<Patient> patient1 = this.getPatient(id);
        if(patient1.isPresent()){
            Patient patientDB = patient1.orElseThrow();
            patientDB.setName(patient.getName());
            patientDB.setDateOfBirth(patient.getDateOfBirth());
            patientDB.setGender(patient.getGender());
            patientDB.setRfc(patient.getRfc());
            patientDB.setAddress(patient.getAddress());
            patientDB.setCity(patient.getCity());
            patientDB.setPhoneNumber(patient.getPhoneNumber());
            patientDB.setEmail(patient.getEmail());
            this.savePatient(patientDB);
            return Optional.of(patientDB);
        }
        return Optional.empty();
    }

    /** The deletePatient() method is responsible for deleting a specific patient from the database based on their ID.
     * It accomplishes this by calling the deleteById method from the PatientRepository class.
     * This deleteById() method specifically targets and removes the Patient record associated with the provided ID.
     */
    public void deletePatient(int id){
        patientRepository.deleteById(id);
    }
}
