package com.iwa.iwaid.consultoriomedico.dto;

import com.iwa.iwaid.consultoriomedico.entity.DosageForm;
import com.iwa.iwaid.consultoriomedico.entity.Medicine;
import lombok.Builder;
import lombok.Data;
import org.jsondoc.core.annotation.ApiObjectField;

@Data
@Builder
public class MedicineDTO {
    @ApiObjectField(name = "id", description = "Medicine's id")
    private int id;

    @ApiObjectField(name = "code", description = "Medicine's code")
    private String code;

    @ApiObjectField(name = "name", description = "Medicine's name")
    private String name;

    @ApiObjectField(name = "dose", description = "Medicine's dose")
    private String dose;

    @ApiObjectField(name = "doseForms", description = "Medicine's dosage forms")
    private DosageForm dosageForms;

    @ApiObjectField(name = "description", description = "Medicine's description")
    private String description;

    @ApiObjectField(name = "quantity", description = "Medicine's quantity")
    private int quantity;

    public static MedicineDTO build(final Medicine medicine) {
        return MedicineDTO.builder()
                .id(medicine.getId())
                .code(medicine.getCode())
                .name(medicine.getName())
                .dose(medicine.getDose())
                .dosageForms(medicine.getDosageForms())
                .description(medicine.getDescription())
                .quantity(medicine.getQuantity())
                .build();
    }
}
