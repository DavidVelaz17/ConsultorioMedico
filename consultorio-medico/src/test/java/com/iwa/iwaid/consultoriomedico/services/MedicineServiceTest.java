package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.MedicineDTO;
import com.iwa.iwaid.consultoriomedico.entity.DosageForm;
import com.iwa.iwaid.consultoriomedico.entity.Medicine;
import com.iwa.iwaid.consultoriomedico.form.MedicineFiltersForm;
import com.iwa.iwaid.consultoriomedico.form.MedicineForm;
import com.iwa.iwaid.consultoriomedico.repository.MedicineRepository;
import com.iwa.iwaid.consultoriomedico.repository.MedicineSpecs;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@RequiredArgsConstructor
public class MedicineServiceTest {
    @Mock
    private MedicineService medicineService;
    @Mock
    private MedicineRepository medicineRepository;
    private MedicineForm medicineForm;
    private MedicineFiltersForm medicineFiltersForm;
    private Medicine medicine;
    private MedicineDTO medicineDTO;

    @Before
    public void setUp() {
        medicineForm = new MedicineForm();
        medicineForm.setName("Paracetamol");
        medicineForm.setCode("PAR50");
        medicineForm.setDose("50 mg");
        medicineForm.setDosageForms(DosageForm.Pastilla);
        medicineForm.setDescription("Is a nonsteroidal anti-inflammatory drug (NSAID)");
        medicineForm.setQuantity(100);

        medicine = new Medicine(medicineForm);

        medicineFiltersForm = new MedicineFiltersForm();
        medicineFiltersForm.setName("Paracetamol");
        medicineFiltersForm.setCode("PARAC");
        medicineFiltersForm.setDosageForms(DosageForm.Pastilla);
    }

    @Test
    public void getAllByFilters() {
        when(medicineRepository.findAll(MedicineSpecs.getAllByFilters(medicineFiltersForm)))
                .thenReturn(List.of(medicine));
        List<MedicineDTO> medicines = medicineService.getAllByFilters(medicineFiltersForm);
        assertThat(medicineService.getAllByFilters(medicineFiltersForm)).isEqualTo(medicines);
    }

    @Test
    public void getMedicineById() throws Exception {
        when(medicineRepository.findById(anyInt())).thenReturn(Optional.of(medicine));
        medicineDTO = medicineService.getMedicineById(anyInt());
        assertThat(medicineService.getMedicineById(anyInt())).isEqualTo(medicineDTO);
    }

    @Test
    public void createMedicine() {
        when(medicineRepository.save(medicine)).thenReturn(medicine);
        medicineDTO = medicineService.createMedicine(medicineForm);
        assertThat(medicineService.createMedicine(medicineForm)).isEqualTo(medicineDTO);
    }

    @Test
    public void updateMedicine() throws Exception {
        when(medicineRepository.findById(anyInt())).thenReturn(Optional.of(medicine));
        when(medicineRepository.save(medicine)).thenReturn(medicine);
        medicineDTO = medicineService.updateMedicine(medicineForm, 1);
        assertThat(medicineService.updateMedicine(medicineForm, 1)).isEqualTo(medicineDTO);
    }
}