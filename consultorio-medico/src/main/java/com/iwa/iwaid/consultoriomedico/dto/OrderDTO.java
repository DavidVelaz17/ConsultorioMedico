package com.iwa.iwaid.consultoriomedico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jsondoc.core.annotation.ApiObjectField;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTO {
    @ApiObjectField(name = "amount",description = "Order's amount")
    private double amount;

    @ApiObjectField(name = "currency",description = "Order's currency")
    private String currency;

    @ApiObjectField(name = "method",description = "Order's method")
    private String method;

    @ApiObjectField(name = "intent",description = "Order's intent")
    private String intent;

    @ApiObjectField(name = "description",description = "Order's description")
    private String description;
}
