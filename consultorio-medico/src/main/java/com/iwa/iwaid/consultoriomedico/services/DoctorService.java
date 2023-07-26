package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.DoctorDTO;
import com.iwa.iwaid.consultoriomedico.entity.Doctor;
import com.iwa.iwaid.consultoriomedico.form.DoctorForm;
import com.iwa.iwaid.consultoriomedico.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorDTO getDoctor(final int id) throws Exception {
        validateIfDoctorExists(id);
        Doctor doctor = doctorRepository.findById(id).get();
        return DoctorDTO.build(doctor);
    }

    public DoctorDTO saveDoctor(final DoctorForm form) {
        final Doctor doctor = new Doctor(form);
        doctorRepository.save(doctor);
        return DoctorDTO.build(doctor);
    }

    public void deleteDoctor(final int id) throws Exception {
        validateIfDoctorExists(id);
        doctorRepository.deleteById(id);
    }

    public DoctorDTO updateDoctorById(final DoctorForm form, final int id) throws Exception {
        validateIfDoctorExists(id);
        final Doctor doctor = doctorRepository.findById(id).get();
        doctor.updateDoctor(form);
        doctorRepository.save(doctor);
        return DoctorDTO.build(doctor);
    }

    private void validateIfDoctorExists(int id) throws Exception {
        if(!doctorRepository.existsById(id)){
            throw new Exception("{not.found}");
        }
    }
}
