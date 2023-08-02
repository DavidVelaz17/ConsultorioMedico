package com.iwa.iwaid.consultoriomedico.convertors;

import com.iwa.iwaid.consultoriomedico.entity.Hour;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class HourToIntConvertor implements AttributeConverter<Hour,Integer> {
    @Override
    public Integer convertToDatabaseColumn(Hour hour) {
        if(hour==null){
            return null;
        }
        return hour.getKey();
    }

    @Override
    public Hour convertToEntityAttribute(Integer key) {
        if (key==null){
            return null;
        }
        return  Hour.getWrittenHour(key);
    }
}
