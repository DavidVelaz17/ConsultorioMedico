package com.iwa.iwaid.consultoriomedico.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
    private int key;
    Hour(int key){this.key=key;}

    public static Hour getWrittenHour(int key){
        switch (key){
            case 8:
                return Hour.EIGHT_AM;
            case 9:
                return Hour.NINE_AM;
            case 10:
                return Hour.TEN_AM;
            case 11:
                return Hour.ELEVEN_AM;
            case 12:
                return Hour.TWELVE_PM;
            case 13:
                return Hour.ONE_PM;
            case 14:
                return Hour.TWO_PM;
            case 15:
                return Hour.THREE_PM;
            case 16:
                return Hour.FOUR_PM;
            case 17:
                return Hour.FIVE_PM;
            case 18:
                return Hour.SIX_PM;
            case 19:
                return Hour.SEVEN_PM;
            case 20:
                return Hour.EIGHT_PM;
            default:
                throw new IllegalArgumentException("Hour [" + key
                        + "] not supported.");
        }
    }

}
