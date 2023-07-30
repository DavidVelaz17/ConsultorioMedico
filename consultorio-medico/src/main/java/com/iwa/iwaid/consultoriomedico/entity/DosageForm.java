package com.iwa.iwaid.consultoriomedico.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DosageForm {
    Liquida(0), Pastilla(1), Capsula(2), Gel(3), Crema(4), Supositorio(5);
    private int key;

    DosageForm(int key) {
        this.key = key;
    }

    public static DosageForm getDosageForms(int key) {
        switch (key) {
            case 0:
                return DosageForm.Liquida;
            case 1:
                return DosageForm.Pastilla;
            case 2:
                return DosageForm.Capsula;
            case 3:
                return DosageForm.Gel;
            case 4:
                return DosageForm.Crema;
            case 5:
                return DosageForm.Supositorio;
            default:
                throw new IllegalArgumentException("Key [" + key
                        + "] not supported.");
        }
    }
}
