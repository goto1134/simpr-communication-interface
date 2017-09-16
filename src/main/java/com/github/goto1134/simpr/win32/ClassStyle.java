package com.github.goto1134.simpr.win32;

import jnr.ffi.util.EnumMapper;

/**
 * Class styles
 */
public enum ClassStyle
        implements EnumMapper.IntegerEnum {

    VREDRAW(0x0001),
    HREDRAW(0x0002),
    DBLCLKS(0x0008),
    OWNDC(0x0020),
    CLASSDC(0x0040),
    PARENTDC(0x0080),
    NOCLOSE(0x0200),
    SAVEBITS(0x0800),
    BYTEALIGNCLIENT(0x1000),
    BYTEALIGNWINDOW(0x2000),
    GLOBALCLASS(0x4000),
    IME(0x00010000),
    DROPSHADOW(0x00020000);

    private final int value;

    ClassStyle(int value) {

        this.value = value;
    }

    @Override
    public int intValue() {
        return value;
    }
}
