package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.MedicalHistoryDTO;
import com.iwa.iwaid.consultoriomedico.dto.PatientDTO;
import com.iwa.iwaid.consultoriomedico.entity.MedicalHistory;
import com.iwa.iwaid.consultoriomedico.form.MedicalHistoryForm;
import com.iwa.iwaid.consultoriomedico.repository.MedicalHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MedicalHistoryService {

    private final MedicalHistoryRepository repository;
    private final PatientService patientService;

    public List<MedicalHistoryDTO> findAllMedicalHistorys() {
        final List<MedicalHistory> histories = repository.findAll();
        final Map<Integer, PatientDTO> patientDTOMap =
                getPatientsMap(histories.stream().map(MedicalHistory::getPatientsId).toList());
        return histories
                .stream().map(history -> MedicalHistoryDTO
                        .build(history, patientDTOMap.get(history.getPatientsId())))
                .toList();
    }

    public MedicalHistoryDTO findMedicalHistorybyId(final int id) throws Exception {
        validateIfMedicalHistoryExist(id);
        MedicalHistory medicalHistory = repository.findById(id).orElseThrow();
        return MedicalHistoryDTO.build(medicalHistory);
    }

    public MedicalHistoryDTO createMedicalHistory(final MedicalHistoryForm form) throws Exception {
        patientService.validateIfPatientExist(form.getPatientsId());
        MedicalHistory medicalHistory = new MedicalHistory(form);
        repository.save(medicalHistory);
        return MedicalHistoryDTO.build(medicalHistory);
    }

    public MedicalHistoryDTO updateMedicalHistory(final MedicalHistoryForm form, final int id) throws Exception {
        validateIfMedicalHistoryExist(id);
        patientService.validateIfPatientExist(form.getPatientsId());
        final MedicalHistory medicalHistory = repository.findById(id).orElseThrow();
        medicalHistory.updateMedicalHistory(form);
        repository.save(medicalHistory);
        return MedicalHistoryDTO.build(medicalHistory);
    }

    public void deleteMedicalHistory(final int id) throws Exception {
        validateIfMedicalHistoryExist(id);
        repository.deleteById(id);
    }

    public void validateIfMedicalHistoryExist(final int id) throws Exception {
        if (!repository.existsById(id)) {
            throw new Exception("medical history not found with ID: " + id);
        }
    }

    private Map<Integer, PatientDTO> getPatientsMap(final List<Integer> patientsIds) {
        return patientService.getPatientByIds(patientsIds);
    }

}
