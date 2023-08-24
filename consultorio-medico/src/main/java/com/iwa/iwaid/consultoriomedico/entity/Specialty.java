package com.iwa.iwaid.consultoriomedico.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Specialty {
    General(0), Urologia(1), Ginecologia(2), Pediatria(3), Neurologia(4);
    private final int key;
    public static Specialty getSpecialty(Integer key) {
        Map<Integer,Specialty> specialtyMap =
                Arrays.stream(Specialty.values()).collect(Collectors.toMap(Specialty::getKey, Function.identity()));
        return specialtyMap.get(key);
    }
}
