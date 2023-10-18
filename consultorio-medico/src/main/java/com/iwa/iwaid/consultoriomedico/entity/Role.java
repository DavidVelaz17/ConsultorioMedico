package com.iwa.iwaid.consultoriomedico.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Role {
    Doctor(0), Patient(1);
    private final int key;
    public static Role getRole(Integer key){
        Map<Integer, Role> RolMap =
                Arrays.stream(Role.values()).collect(Collectors.toMap(Role::getKey, Function.identity()));
        return RolMap.get(key);
    }
}
