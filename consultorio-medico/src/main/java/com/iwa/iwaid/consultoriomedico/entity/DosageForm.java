package com.iwa.iwaid.consultoriomedico.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum DosageForm {
    Liquida(0), Pastilla(1), Capsula(2), Gel(3), Crema(4), Supositorio(5);
    private final int key;
    public static DosageForm getDosageForms(int key) {
        Map<Integer,DosageForm> dosageFormsMap =
          Arrays.stream(
             DosageForm.values()).collect(Collectors.toMap(DosageForm::getKey, Function.identity()));
        return dosageFormsMap.get(key);
    }
}
