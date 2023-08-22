package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.MedicineDTO;
import com.iwa.iwaid.consultoriomedico.entity.Medicine;
import com.iwa.iwaid.consultoriomedico.form.MedicineFiltersForm;
import com.iwa.iwaid.consultoriomedico.form.MedicineForm;
import com.iwa.iwaid.consultoriomedico.repository.MedicineRepository;
import com.iwa.iwaid.consultoriomedico.repository.MedicineSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ResourceBundle;

@Service
@RequiredArgsConstructor
public class MedicineService {
    private final MedicineRepository medicineRepository;
    private final ResourceBundle messages =
            ResourceBundle.getBundle("ValidationMessages");

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
            throw new Exception(messages.getString("not.found") + " -Medicine:" + medicineId);
        }
    }
}
