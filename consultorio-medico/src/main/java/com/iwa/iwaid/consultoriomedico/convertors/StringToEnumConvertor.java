package com.iwa.iwaid.consultoriomedico.convertors;

import com.iwa.iwaid.consultoriomedico.entity.Specialty;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConvertor implements Converter<String, Specialty> {
    @Override
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
}
