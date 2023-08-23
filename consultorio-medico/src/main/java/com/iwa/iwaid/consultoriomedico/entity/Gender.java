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
public enum Gender {
    Masculino(0), Femenino(1), Otro(2);
    private final int key;
    public static Gender getGender(Integer key) {
        Map<Integer, Gender> genderMap =
          Arrays.stream(Gender.values()).collect(Collectors.toMap(Gender::getKey, Function.identity()));
        return genderMap.get(key);
    }
}
