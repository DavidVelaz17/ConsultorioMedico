package com.iwa.iwaid.consultoriomedico.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.iwa.iwaid.consultoriomedico.entity.Patient;
import com.iwa.iwaid.consultoriomedico.services.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientControllerTest.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    /**TODO IWAID-21
    @Test
    void testGetPatientById() throws Exception {
        Patient patient = getPatient();
        // Aquí se utiliza Mockito para definir el comportamiento del servicio
        when(patientService.getPatient(1)).thenReturn(Optional.of(patient));
        mockMvc.perform(get("/iwaid/patients/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Irving F\"}"));
    }

    public Patient getPatient(){
        Patient patient = new Patient();
        patient.setId(1);
        patient.setName("Irving F");
        patient.setDateOfBirth(LocalDate.of(1999,01,01));
        patient.setGender("Hombre");
        patient.setRfc("abcd0101acf");
        patient.setAddress("Av. Palm");
        patient.setCity("Nogales");
        patient.setPhoneNumber(2722102222L);
        patient.setEmail("irving@email.com");
        return patient;
    }
    */
}
