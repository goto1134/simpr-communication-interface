package com.github.goto1134.simpr.win32;

import static jnr.ffi.util.EnumMapper.*;

/**
 * Created by Andrew
 * on 17.09.2017.
 */
public enum PowerMessageStatus
        implements IntegerEnum {
    ;
    private final int value;

    PowerMessageStatus(int value) {
        this.value = value;
    }

    @Override
    public int intValue() {
        return this.value;
    }
}
