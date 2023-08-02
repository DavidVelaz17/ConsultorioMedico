package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.DoctorDTO;
import com.iwa.iwaid.consultoriomedico.entity.Doctor;
import com.iwa.iwaid.consultoriomedico.entity.Specialty;
import com.iwa.iwaid.consultoriomedico.form.DoctorFiltersForm;
import com.iwa.iwaid.consultoriomedico.form.DoctorForm;
import com.iwa.iwaid.consultoriomedico.repository.DoctorRepository;
import com.iwa.iwaid.consultoriomedico.repository.DoctorSpecs;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest
@RunWith(SpringRunner.class)
@RequiredArgsConstructor
public class DoctorServiceTest {
    @Mock
    private DoctorService doctorService;
    @Mock
    private DoctorRepository doctorRepository;
    private DoctorForm doctorForm;
    private DoctorFiltersForm doctorFiltersForm;
    private Doctor doctor;
    private DoctorDTO doctorDTO;

    @Before
    public void setUp() throws Exception {
        doctorForm = new DoctorForm();
        doctorForm.setName("Juan");
        doctorForm.setSpecialty(Specialty.valueOf("Pediatria"));
        doctorForm.setAddress("Soledad #54");
        doctorForm.setPhoneNumber("2837485968");
        doctorForm.setEmail("Juan54@gmail.com");

        doctorFiltersForm = new DoctorFiltersForm();
        doctorFiltersForm.setName("Juan");
        doctorFiltersForm.setSpecialty(Specialty.valueOf("Pediatria"));
    }

    @Test
    public void getAllByFilters() throws Exception {
        doctor = new Doctor(doctorForm);
        Mockito.when(doctorRepository.findAll(DoctorSpecs.getAllByFilters(doctorFiltersForm)))
                .thenReturn(List.of(doctor));
        List<DoctorDTO> doctors = doctorService.getAllByFilters(doctorFiltersForm);
        assertThat(doctorService.getAllByFilters(doctorFiltersForm)).isEqualTo(doctors);
    }

    @Test
    public void getDoctorById() throws Exception {
        doctor = new Doctor(doctorForm);
        Mockito.when(doctorRepository.findById(anyInt())).thenReturn(Optional.of(doctor));
        doctorDTO = doctorService.getDoctorById(anyInt());
        assertThat(doctorService.getDoctorById(anyInt())).isEqualTo(doctorDTO);
    }

    @Test
    public void createDoctor() {
        doctor = new Doctor(doctorForm);
        Mockito.when(doctorRepository.save(doctor)).thenReturn(doctor);
        doctorDTO = doctorService.createDoctor(doctorForm);
        assertThat(doctorService.createDoctor(doctorForm)).isEqualTo(doctorDTO);
    }

    @Test
    public void deleteDoctor() throws Exception {
        Mockito.doAnswer(Answers.CALLS_REAL_METHODS).when(doctorRepository).deleteById(anyInt());
        boolean result = doctorService.deleteDoctor(anyInt());
        assertThat(doctorService.deleteDoctor(anyInt())).isEqualTo(result);
    }

    @Test
    public void updateDoctor() throws Exception {
        doctor = new Doctor(doctorForm);
        Mockito.when(doctorRepository.findById(anyInt())).thenReturn(Optional.of(doctor));
        Mockito.when(doctorRepository.save(doctor)).thenReturn(doctor);
        doctorDTO = doctorService.updateDoctor(doctorForm, 1);
        assertThat(doctorService.updateDoctor(doctorForm, 1)).isEqualTo(doctorDTO);
    }
}