package com.iwa.iwaid.consultoriomedico.dto;

import com.iwa.iwaid.consultoriomedico.entity.Medicine;
import lombok.Builder;
import lombok.Data;
import org.jsondoc.core.annotation.ApiObjectField;

@Data
@Builder
public class MedicineDTO {
    @ApiObjectField(name = "id",description = "Medicine's id")
    private int id;

    @ApiObjectField(name = "name",description = "Medicine's name")
    private String name;

    @ApiObjectField(name = "dose",description = "Medicine's dose")
    private String dose;

    @ApiObjectField(name = "packaging",description = "Medicine's packaging")
    private String packaging;

    @ApiObjectField(name = "description",description = "Medicine's description")
    private String description;

    public static MedicineDTO build(final Medicine medicine){
        return MedicineDTO.builder()
                .id(medicine.getId())
                .name(medicine.getName())
                .dose(medicine.getDose())
                .packaging(medicine.getPackaging())
                .description(medicine.getDescription())
                .build();
    }
}
