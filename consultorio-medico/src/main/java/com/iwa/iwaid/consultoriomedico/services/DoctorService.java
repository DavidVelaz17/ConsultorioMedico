package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.DoctorDTO;
import com.iwa.iwaid.consultoriomedico.entity.Doctor;
import com.iwa.iwaid.consultoriomedico.form.DoctorFiltersForm;
import com.iwa.iwaid.consultoriomedico.form.DoctorForm;
import com.iwa.iwaid.consultoriomedico.repository.DoctorRepository;
import com.iwa.iwaid.consultoriomedico.repository.DoctorSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public List<DoctorDTO> getAllByFilters(final DoctorFiltersForm form) {
        final List<Doctor> doctors = doctorRepository.findAll(DoctorSpecs.getAllByFilters(form));
        return doctors.stream().map(DoctorDTO::build).toList();
    }

    public DoctorDTO getDoctorById(final int id) throws Exception {
        validateIfDoctorExists(id);
        Doctor doctor = doctorRepository.findById(id).get();
        return DoctorDTO.build(doctor);
    }

    public DoctorDTO createDoctor(final DoctorForm form) {
        final Doctor doctor = new Doctor(form);
        doctorRepository.save(doctor);
        return DoctorDTO.build(doctor);
    }

    public void deleteDoctor(final int id) throws Exception {
        validateIfDoctorExists(id);
        doctorRepository.deleteById(id);
    }

    public DoctorDTO updateDoctor(final DoctorForm form, final int id) throws Exception {
        validateIfDoctorExists(id);
        final Doctor doctor = doctorRepository.findById(id).get();
        doctor.updateDoctor(form);
        doctorRepository.save(doctor);
        return DoctorDTO.build(doctor);
    }

    public Map<Integer, DoctorDTO> getDoctorsByIds(final List<Integer> doctorIds) {
        final List<Doctor> doctors = doctorRepository.findAllById(doctorIds);
        final List<DoctorDTO> doctorDTOs = doctors.stream().map(DoctorDTO::build).toList();
        return doctorDTOs.stream().collect(Collectors.toMap(DoctorDTO::getId,Function.identity()));
    }

    private void validateIfDoctorExists(int id) throws Exception {
        if (!doctorRepository.existsById(id)) {
            throw new Exception("Doctor not found");
        }
    }
}
