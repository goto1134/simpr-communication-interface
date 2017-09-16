package com.github.goto1134.simpr.win32;

import jnr.ffi.util.EnumMapper;

/**
 * Created by Andrew
 * on 17.09.2017.
 */
public enum WindowStyle
        implements EnumMapper.IntegerEnum {
    OVERLAPPED(0x00000000),
    POPUP(0x80000000),
    CHILD(0x40000000),
    MINIMIZE(0x20000000),
    VISIBLE(0x10000000),
    DISABLED(0x08000000),
    CLIP_SIBLINGS(0x04000000),
    CLIP_CHILDREN(0x02000000),
    MAXIMIZE(0x01000000),
    CAPTION(0x00C00000),
    BORDER(0x00800000),
    DLG_FRAME(0x00400000),
    V_SCROLL(0x00200000),
    H_SCROLL(0x00100000),
    SYS_MENU(0x00080000),
    THICK_FRAME(0x00040000),
    GROUP(0x00020000),
    TAB_STOP(0x00010000),

    MINIMIZE_BOX(0x00020000),
    MAXIMIZE_BOX(0x00010000),


    TILED(OVERLAPPED.value),
    ICONIC(MINIMIZE.value),
    SIZE_BOX(THICK_FRAME.value),
    OVERLAPPED_WINDOW(OVERLAPPED.value | CAPTION.value | SYS_MENU.value |
                              THICK_FRAME.value | MINIMIZE_BOX.value | MAXIMIZE_BOX.value),

    POPUP_WINDOW(POPUP.value | BORDER.value | SYS_MENU.value),
    CHILD_WINDOW(CHILD.value),
    TILED_WINDOW(OVERLAPPED_WINDOW.value);

    private final int value;

    WindowStyle(int value) {this.value = value;}

    @Override
    public int intValue() {
        return this.value;
    }
}
