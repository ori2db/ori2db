package com.c2uol.enums;

import java.util.Calendar;

public enum DateEnum {


    YEAR(Calendar.YEAR), MONTH(Calendar.MONTH), DAY(Calendar.DAY_OF_MONTH), HOUR(Calendar.HOUR_OF_DAY), MINUTE(
            Calendar.MINUTE), SECOND(Calendar.SECOND);

    private int value;

    private DateEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
