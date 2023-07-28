package com.iwa.iwaid.consultoriomedico.convertors;

import com.iwa.iwaid.consultoriomedico.entity.Specialty;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Converter(autoApply = true)
public class StringToEnumConvertor implements AttributeConverter<Specialty, Integer> {
    @Override
    public Integer  convertToDatabaseColumn(Specialty s) {
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

 /*   @Override
    public Specialty convert(String s) {
        if (s.equals("Neurologia")) {
            return Specialty.Neurologia;
        } else if (s.equals("Ginecologia")) {
            return Specialty.Ginecologia;
        } else if (s.equals("Urologia")) {
            return Specialty.Urologia;
        } else if (s.equals("Pediatria")) {
            return Specialty.Pediatria;
        } else if(s.equals("General")){
            return Specialty.General;
        }
        return null;
    }
    */

}
