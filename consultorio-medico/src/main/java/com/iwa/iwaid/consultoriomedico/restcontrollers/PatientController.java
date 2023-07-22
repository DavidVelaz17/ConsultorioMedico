package com.iwa.iwaid.consultoriomedico.restcontrollers;

import com.iwa.iwaid.consultoriomedico.entity.Patient;
import com.iwa.iwaid.consultoriomedico.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/** The PatientController class, marked with the '@RestController' annotation, is a REST controller
 *  where every method returns a domain object instead of a view.
 * It's mapped to handle web requests at the '/iwaid/patients/' path, as specified by the @RequestMapping annotation.
 */
@RestController
@RequestMapping(path = "/iwaid/patients/")
public class PatientController {

    /** The PatientService is injected into this controller using the '@Autowired' annotation.
     * The constructor is used to assign the PatientService to a final field in the controller.
     */
    @Autowired
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /** The getbyId() method, mapped to a GET request at the '/iwaid/patients/{id} path, retrieves a specific
     *  patient by their ID.
     * It calls the getPatient method from PatientService, passing the ID from the path variable.
     * If a patient with the given ID exists, it returns the patient and a status of OK.
     * If not, it returns a 'Patient not found' message and a status of NOT FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getbyId(@PathVariable int id){
        Optional<Patient> patient = patientService.getPatient(id);
        if(patient.isPresent()){
            return new ResponseEntity<>(patient.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
        }
    }

    /** The save() method, mapped to a POST request at the '/iwaid/patients/' path,
     * saves a new patient to the database.
     * It calls the savePatient method from PatientService, passing the Patient object from the request body.
     * It returns the ID of the saved patient and a status of CREATED.
     */
    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Patient patient){
        int id = patientService.savePatient(patient);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    /**The update() method, mapped to a PUT request at the '/iwaid/patients/{id}' path, }
     * updates an existing patient in the database.
     * It achieves this by calling the updatePatient method from the PatientService class,
     * passing the Patient object from the request body and the ID from the path variable.
     * The updatePatient() method returns an Optional containing the updated patient if the update is successful,
     * or an empty Optional if the patient does not exist.
     * If the update is successful, this method returns a status of CREATED.
     * If the patient does not exist, it returns a status of NOT FOUND.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Patient patient, @PathVariable int id){
        Optional<Patient> patient1 = patientService.updatePatient(patient, id);
        if(patient1.isPresent()){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /** The delete() method, mapped to a DELETE request at the '/iwaid/patients/{id}' path,
     * deletes a specific patient from the database based on their ID.
     * It first retrieves the existing patient by their ID. If the patient exists,
     * it calls the deletePatient method from the PatientService class to delete the patient,
     * and returns a status of OK.
     * If the patient does not exist, it returns a status of NOT FOUND.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        Optional<Patient> patient = patientService.getPatient(id);
        if(patient.isPresent()){
            patientService.deletePatient(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
