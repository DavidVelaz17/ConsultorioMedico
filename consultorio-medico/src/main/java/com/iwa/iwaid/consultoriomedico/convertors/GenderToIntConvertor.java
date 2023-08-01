package com.iwa.iwaid.consultoriomedico.convertors;

import com.iwa.iwaid.consultoriomedico.entity.Gender;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenderToIntConvertor implements AttributeConverter<Gender, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Gender gender) {
        if (gender == null) {
            return null;
        }
        return gender.getKey();
    }

    @Override
    public Specialty convertToEntityAttribute(Integer key) {
        if (key == null) {
            return null;
        }
        return Gender.getSpecialty(key);
    }
