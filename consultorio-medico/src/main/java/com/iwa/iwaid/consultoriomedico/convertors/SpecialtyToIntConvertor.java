package com.iwa.iwaid.consultoriomedico.convertors;

import com.iwa.iwaid.consultoriomedico.entity.Specialty;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SpecialtyToIntConvertor implements AttributeConverter<Specialty, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Specialty s) {
        if (s == null) {
            return null;
        }
        return s.getKey();
    }

    @Override
    public Specialty convertToEntityAttribute(Integer key) {
        if (key == null) {
            return null;
        }
        return Specialty.getSpecialty(key);
    }
}
