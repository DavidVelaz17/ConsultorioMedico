package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.MedicineDTO;
import com.iwa.iwaid.consultoriomedico.entity.Medicine;
import com.iwa.iwaid.consultoriomedico.form.MedicineForm;
import com.iwa.iwaid.consultoriomedico.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineDTO getMedicine(final int id) throws Exception {
        validateIfMedicineExists(id);
        Medicine medicine = medicineRepository.findById(id).get();
        return MedicineDTO.build(medicine);
    }

    public MedicineDTO saveMedicine(final MedicineForm form) {
        final Medicine medicine = new Medicine(form);
        medicineRepository.save(medicine);
        return MedicineDTO.build(medicine);
    }

    public void deleteMedicine(final int id) throws Exception {
        validateIfMedicineExists(id);
        medicineRepository.deleteById(id);
    }

    public MedicineDTO updateMedicineById(final MedicineForm form, final int id) throws Exception {
        validateIfMedicineExists(id);
        final Medicine medicine = medicineRepository.findById(id).get();
        medicine.updateMedicine(form);
        medicineRepository.save(medicine);
        return MedicineDTO.build(medicine);
    }

    private void validateIfMedicineExists(int id) throws Exception {
        if (!medicineRepository.existsById(id)) {
            throw new Exception("{not.found}");
        }
    }
}
