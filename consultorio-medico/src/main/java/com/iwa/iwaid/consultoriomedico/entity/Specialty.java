package com.iwa.iwaid.consultoriomedico.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum Specialty {
    General(0), Urologia(1), Ginecologia(2), Pediatria(3), Neurologia(4);
    private int key;
    Specialty(int key) {
    this.key=key;
    }

    public static Specialty getSpecialty(Integer key) {
        switch (key){
            case 0:
                return Specialty.General;
            case 1:
                return Specialty.Urologia;
            case 2:
                return Specialty.Ginecologia;
            case 3:
                return Specialty.Pediatria;
            case 4:
                return Specialty.Neurologia;
            default:
                throw new IllegalArgumentException("Key [" + key
                        + "] not supported.");
        }
    }
}
