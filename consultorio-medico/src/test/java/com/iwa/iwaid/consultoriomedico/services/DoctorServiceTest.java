package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.entity.Doctors;
import com.iwa.iwaid.consultoriomedico.repository.DoctorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {
    @InjectMocks
    DoctorService doctorService;
    @Mock
    private DoctorRepository doctorRepository;
    AutoCloseable autoCloseable;
   // Doctors doctors;
    @BeforeEach
    void setUp() {
        autoCloseable= MockitoAnnotations.openMocks(this);
        //doctorService= new DoctorService();

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }
/*  TODO IWAID-20
    @Test
    void getDoctor() {
        //Optional<Doctors> optional=Optional.of(doctors);
       Doctors doctors=new Doctors(1,"eduardo","surgery","noche #32",12312332L,"dasdlkfj@mlksmdf.com");
        when(doctorRepository.findById(1)).thenReturn(Optional.ofNullable(doctors));
        Optional<Doctors> doctors1=doctorService.getDoctor(1);
        assertNotNull(doctors1);
       // assertEquals(doctors,doctors1);
    }*/

    @Test
    void saveDoctor() {

    }

    @Test
    void deleteDoctor() {
    }

    @Test
    void updateDoctorById() {
    }
}