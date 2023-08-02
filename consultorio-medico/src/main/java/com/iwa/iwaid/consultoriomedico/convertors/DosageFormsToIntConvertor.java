package com.iwa.iwaid.consultoriomedico.convertors;

import com.iwa.iwaid.consultoriomedico.entity.DosageForm;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DosageFormsToIntConvertor implements AttributeConverter<DosageForm, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DosageForm dosageForm) {
        if (dosageForm == null) {
            return null;
        }
        return dosageForm.getKey();
    }

    @Override
    public DosageForm convertToEntityAttribute(Integer key) {
        if (key == null) {
            return null;
        }
        return DosageForm.getDosageForms(key);
    }
}
