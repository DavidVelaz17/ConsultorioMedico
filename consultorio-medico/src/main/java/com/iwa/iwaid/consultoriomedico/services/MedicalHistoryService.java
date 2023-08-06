package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.MedicalHistoryDTO;
import com.iwa.iwaid.consultoriomedico.entity.MedicalHistory;
import com.iwa.iwaid.consultoriomedico.form.MedicalHistoryForm;
import com.iwa.iwaid.consultoriomedico.repository.MedicalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalHistoryService {

    @Autowired
    private MedicalHistoryRepository repository;

    @Autowired
    private PatientService patientService;

    public List<MedicalHistoryDTO> findAllMedicalHistorys() {
        List<MedicalHistory> histories = repository.findAll();
        return histories.stream().map(MedicalHistoryDTO::build).toList();
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
            throw new Exception("Medical history ID does not exist");
        }
    }

}
