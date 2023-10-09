package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.MedicalHistoryDTO;
import com.iwa.iwaid.consultoriomedico.dto.PatientDTO;
import com.iwa.iwaid.consultoriomedico.entity.MedicalHistory;
import com.iwa.iwaid.consultoriomedico.form.MedicalHistoryForm;
import com.iwa.iwaid.consultoriomedico.repository.MedicalHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:ValidationMessages.properties")
public class MedicalHistoryService {

    private final MedicalHistoryRepository repository;
    private final PatientService patientService;

    @Value("not.found")
    private String notFound;

    public List<MedicalHistoryDTO> getAllMedicalHistories() {
        final List<MedicalHistory> histories = repository.findAll();
        final Map<Integer, PatientDTO> patientDTOMap =
                getPatientsMap(histories.stream().map(MedicalHistory::getPatientsId).toList());
        return histories
                .stream().map(history -> MedicalHistoryDTO
                        .build(history, patientDTOMap.get(history.getPatientsId())))
                .toList();
    }

    public MedicalHistoryDTO getMedicalHistoryById(final int medicalHistoryId)
            throws Exception {
        validateIfMedicalHistoryExist(medicalHistoryId);
        final MedicalHistory medicalHistory = repository.findById(medicalHistoryId).orElseThrow();
        return MedicalHistoryDTO.build(medicalHistory);
    }

    public MedicalHistoryDTO createMedicalHistory(final MedicalHistoryForm form)
            throws Exception {
        patientService.validateIfPatientExist(form.getPatientId());
        final MedicalHistory medicalHistory = new MedicalHistory(form);
        repository.save(medicalHistory);
        return MedicalHistoryDTO.build(medicalHistory);
    }

    public MedicalHistoryDTO updateMedicalHistory(final MedicalHistoryForm form,
                                                  final int medicalHistoryId)
            throws Exception {
        validateIfMedicalHistoryExist(medicalHistoryId);
        patientService.validateIfPatientExist(form.getPatientId());
        final MedicalHistory medicalHistory = repository.findById(medicalHistoryId).orElseThrow();
        medicalHistory.updateMedicalHistory(form);
        repository.save(medicalHistory);
        return MedicalHistoryDTO.build(medicalHistory);
    }

    public void deleteMedicalHistory(final int medicalHistoryId) throws Exception {
        validateIfMedicalHistoryExist(medicalHistoryId);
        repository.deleteById(medicalHistoryId);
    }

    public void validateIfMedicalHistoryExist(final int medicalHistoryId)
            throws Exception {
        if (!repository.existsById(medicalHistoryId)) {
            throw new Exception(notFound);
        }
    }

    private Map<Integer, PatientDTO> getPatientsMap(final List<Integer> patientsIds) {
        return patientService.getPatientByIds(patientsIds);
    }

}
