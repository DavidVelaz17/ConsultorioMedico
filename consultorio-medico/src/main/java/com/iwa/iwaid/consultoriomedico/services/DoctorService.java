package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.DoctorDTO;
import com.iwa.iwaid.consultoriomedico.entity.Doctor;
import com.iwa.iwaid.consultoriomedico.form.DoctorFiltersForm;
import com.iwa.iwaid.consultoriomedico.form.DoctorForm;
import com.iwa.iwaid.consultoriomedico.repository.DoctorRepository;
import com.iwa.iwaid.consultoriomedico.repository.DoctorSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:ValidationMessages.properties")
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Value("${not.found}")
    private String notFound;

    public List<DoctorDTO> getAllByFilters(final DoctorFiltersForm form) {
        final List<Doctor> doctors =
                doctorRepository.findAll(DoctorSpecs.getAllByFilters(form));
        return doctors.stream().map(DoctorDTO::build).toList();
    }

    public DoctorDTO getDoctorById(final int doctorId) throws Exception {
        validateIfDoctorExists(doctorId);
        final Doctor doctor = doctorRepository.findById(doctorId).get();
        return DoctorDTO.build(doctor);
    }

    public DoctorDTO createDoctor(final DoctorForm form) {
        final Doctor doctor = new Doctor(form);
        doctorRepository.save(doctor);
        return DoctorDTO.build(doctor);
    }

    public void deleteDoctor(final int doctorId) throws Exception {
        validateIfDoctorExists(doctorId);
        doctorRepository.deleteById(doctorId);
    }

    public DoctorDTO updateDoctor(final DoctorForm form, final int doctorId) throws Exception {
        validateIfDoctorExists(doctorId);
        final Doctor doctor = doctorRepository.findById(doctorId).get();
        doctor.updateDoctor(form);
        doctorRepository.save(doctor);
        return DoctorDTO.build(doctor);
    }

    public Map<Integer, DoctorDTO> getDoctorsByIds(final List<Integer> doctorIds) {
        final List<Doctor> doctors = doctorRepository.findAllById(doctorIds);
        return doctorDTOs(doctors);
    }

    public void validateIfDoctorExists(final int doctorId) throws Exception {
        if (!doctorRepository.existsById(doctorId)) {
            throw new Exception(notFound + " -Doctor:" + doctorId);
        }
    }

    private Map<Integer, DoctorDTO> doctorDTOs(final List<Doctor> doctors) {
        final List<DoctorDTO> doctorDTOs =
                doctors.stream().map(DoctorDTO::build).toList();
        return doctorDTOs
                .stream()
                .collect(Collectors.toMap(DoctorDTO::getId, Function.identity()));
    }
}
