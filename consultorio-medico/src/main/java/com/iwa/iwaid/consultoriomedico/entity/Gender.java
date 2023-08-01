package com.iwa.iwaid.consultoriomedico.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    Masculino(0), Femenino(1), Otro(2);
    private int key;

    Gender(int key) {
        this.key = key;
    }

    public static Gender getGender(Integer key) {
        switch (key) {
            case 0:
                return Gender.Masculino;
            case 1:
                return Gender.Femenino;
            case 2:
                return Gender.Otro;
            default:
                throw new IllegalArgumentException("Key [" + key + "] not supported.");
        }
    }
}
