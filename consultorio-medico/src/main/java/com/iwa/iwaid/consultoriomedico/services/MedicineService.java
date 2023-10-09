package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.MedicineDTO;
import com.iwa.iwaid.consultoriomedico.entity.Medicine;
import com.iwa.iwaid.consultoriomedico.form.MedicineFiltersForm;
import com.iwa.iwaid.consultoriomedico.form.MedicineForm;
import com.iwa.iwaid.consultoriomedico.repository.MedicineRepository;
import com.iwa.iwaid.consultoriomedico.repository.MedicineSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:ValidationMessages.properties")
public class MedicineService {
    private final MedicineRepository medicineRepository;

    @Value("${not.found}")
    private String notFound;

    public List<MedicineDTO> getAllByFilters(final MedicineFiltersForm form) {
        final List<Medicine> medicines =
                medicineRepository.findAll(MedicineSpecs.getAllByFilters(form));
        return medicines.stream().map(MedicineDTO::build).toList();
    }

    public MedicineDTO getMedicineById(final int medicineId) throws Exception {
        validateIfMedicineExists(medicineId);
        final Medicine medicine = medicineRepository.findById(medicineId).get();
        return MedicineDTO.build(medicine);
    }

    public MedicineDTO createMedicine(final MedicineForm form) {
        final Medicine medicine = new Medicine(form);
        medicineRepository.save(medicine);
        return MedicineDTO.build(medicine);
    }

    public void deleteMedicine(final int medicineId) throws Exception {
        validateIfMedicineExists(medicineId);
        medicineRepository.deleteById(medicineId);
    }

    public MedicineDTO updateMedicine(final MedicineForm form, final int medicineId) throws Exception {
        validateIfMedicineExists(medicineId);
        final Medicine medicine = medicineRepository.findById(medicineId).get();
        medicine.updateMedicine(form);
        medicineRepository.save(medicine);
        return MedicineDTO.build(medicine);
    }

    private void validateIfMedicineExists(final int medicineId) throws Exception {
        if (!medicineRepository.existsById(medicineId)) {
            throw new Exception(notFound + " -Medicine:" + medicineId);
        }
    }
}
