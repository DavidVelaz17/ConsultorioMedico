package com.iwa.iwaid.consultoriomedico.restcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.iwa.iwaid.consultoriomedico.entity.Doctor;
import com.iwa.iwaid.consultoriomedico.services.DoctorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DoctorController.class)
class DoctorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DoctorService doctorService;
    Doctor doctor;



    @BeforeEach
    void setUp() {
        doctor =new Doctor(2,"juan","surgery","achu #4","2722894059","juan@gmail.com");

    }

    @AfterEach
    void tearDown() {
    }
//TODO IWAID-20
    @Test
    void testGetDoctor() throws Exception {
        when(doctorService.getDoctor(2))
                .thenReturn(Optional.ofNullable(doctor));
       this.mockMvc.perform(get("/iwaid/doctors/2"))
                        .andDo(print()).andExpect(status().isFound());
    }
/*
    @Test
    void testSave() throws Exception {
        ObjectMapper mapper=new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow=mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(doctor);

        when(doctorService.saveDoctor(doctor))
                .thenReturn(doctor.getId());
        this.mockMvc.perform(post("/iwaid/doctors/").contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        when(doctorService.deleteDoctor(doctor,2))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));
        this.mockMvc.perform(delete("/iwaid/doctors/2"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testUpdateById() throws Exception {
        ObjectMapper mapper=new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow=mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(doctors);

        when(doctorService.updateDoctorById(doctors,2))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));
        this.mockMvc.perform(patch("/iwaid/doctors/2").contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }
 */
}