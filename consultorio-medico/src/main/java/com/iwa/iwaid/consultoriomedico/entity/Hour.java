package com.iwa.iwaid.consultoriomedico.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Hour {
  EIGHT_AM(8),
  NINE_AM(9),
  TEN_AM(10),
  ELEVEN_AM(11),
  TWELVE_PM(12),
  ONE_PM(13),
  TWO_PM(14),
  THREE_PM(15),
  FOUR_PM(16),
  FIVE_PM(17),
  SIX_PM(18),
  SEVEN_PM(19),
  EIGHT_PM(20);
    private final int key;
    public static Hour getWrittenHour(int key){
        Map<Integer,Hour> hourMap =
          Arrays.stream(Hour.values()).collect(Collectors.toMap(Hour::getKey, Function.identity()));
        return hourMap.get(key);
    }

}
