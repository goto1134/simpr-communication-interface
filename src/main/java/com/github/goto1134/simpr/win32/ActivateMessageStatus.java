package com.github.goto1134.simpr.win32;

import static jnr.ffi.util.EnumMapper.IntegerEnum;

/**
 * Status values for {@link WindowMessage#WM_ACTIVATE}
 * Created by Andrew
 * on 17.09.2017.
 */
public enum ActivateMessageStatus
        implements IntegerEnum {
    INACTIVE(0),
    ACTIVE(1),
    CLICK_ACTIVE(2);
    private final int value;

    ActivateMessageStatus(int value) {
        this.value = value;
    }

    @Override
    public int intValue() {
        return this.value;
    }
}
