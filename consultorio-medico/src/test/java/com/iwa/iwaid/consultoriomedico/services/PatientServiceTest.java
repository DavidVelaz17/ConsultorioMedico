package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.PatientDTO;
import com.iwa.iwaid.consultoriomedico.entity.Gender;
import com.iwa.iwaid.consultoriomedico.entity.Patient;
import com.iwa.iwaid.consultoriomedico.form.PatientFilterForm;
import com.iwa.iwaid.consultoriomedico.form.PatientForm;
import com.iwa.iwaid.consultoriomedico.repository.PatientRepository;
import com.iwa.iwaid.consultoriomedico.repository.PatientSpecs;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@RequiredArgsConstructor
public class PatientServiceTest {

    @Mock
    private PatientService patientService;
    @Mock
    private PatientRepository patientRepository;
    private PatientForm form;
    private PatientFilterForm filterForm;
    private Patient patient;
    private PatientDTO patientDTO;

    @Before
    public void setUp() throws Exception {
        form = new PatientForm();
        form.setName("Irving");
        form.setDateOfBirth(LocalDate.of(1999, 01, 01));
        form.setGender(Gender.valueOf("Masculino"));
        form.setRfc("abcd1234acv19");
        form.setAddress("Av. Palm");
        form.setCity("Nogales");
        form.setPhoneNumber("2722100000");
        form.setEmail("irving@email.com");

        patient = new Patient(form);

        filterForm = new PatientFilterForm();
        filterForm.setName("Irving");
        filterForm.setDateOfBirth(List.of(LocalDate.of(1999, 1, 1),
                LocalDate.of(2001, 2, 2)));
        filterForm.setGender(Gender.Masculino);
        filterForm.setCity("Nogales");
        filterForm.setRfc("abcd");
    }

    @Test
    public void findAllPatients() {
        when(patientRepository.findAll()).thenReturn(List.of(patient));
        List<PatientDTO> patients = patientService.findAllPatients();
        assertThat(patientService.findAllPatients()).isEqualTo(patients);
    }

    @Test
    public void getPatientById() throws Exception {
        patient = new Patient(form);
        when(patientRepository.findById(anyInt())).thenReturn(Optional.of(patient));
        patientDTO = patientService.getPatientById(anyInt());
        assertThat(patientService.getPatientById(anyInt())).isEqualTo(patientDTO);
    }

    @Test
    public void getAllByFilters() {
        when(patientRepository.findAll(PatientSpecs.getAllByFilters(filterForm)))
                .thenReturn(List.of(patient));
        List<PatientDTO> patients = patientService.getAllByFilters(filterForm);
        assertThat(patientService.getAllByFilters(filterForm)).isEqualTo(patients);
    }

    @Test
    public void createPatient() {
        patient = new Patient(form);
        when(patientRepository.save(patient)).thenReturn(patient);
        patientDTO = patientService.createPatient(form);
        assertThat(patientService.createPatient(form)).isEqualTo(patientDTO);
    }

    @Test
    public void updatePatient() throws Exception {
        patient = new Patient(form);
        when(patientRepository.findById(anyInt())).thenReturn(Optional.of(patient));
        when(patientRepository.save(patient)).thenReturn(patient);
        patientDTO = patientService.updatePatient(form, 1);
        assertThat(patientService.updatePatient(form, 1)).isEqualTo(patientDTO);
    }
}