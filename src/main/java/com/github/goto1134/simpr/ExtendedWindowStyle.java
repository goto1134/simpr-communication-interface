package com.github.goto1134.simpr;

import jnr.ffi.util.EnumMapper.IntegerEnum;

/**
 * Extended Window Styles
 */
public enum ExtendedWindowStyle
        implements IntegerEnum {
    DLG_MODAL_FRAME(0x00000001),
    NO_PARENT_NOTIFY(0x00000004),
    TOPMOST(0x00000008),
    ACCEPT_FILES(0x00000010),
    TRANSPARENT(0x00000020),
    MDI_CHILD(0x00000040),
    TOOL_WINDOW(0x00000080),
    WINDOW_EDGE(0x00000100),
    CLIENT_EDGE(0x00000200),
    CONTEXT_HELP(0x00000400),
    RIGHT(0x00001000),
    LEFT(0x00000000),
    RTL_READING(0x00002000),
    LTR_READING(0x00000000),
    LEFT_SCROLLBAR(0x00004000),
    RIGHT_SCROLLBAR(0x00000000),
    CONTROL_PARENT(0x00010000),
    STATIC_EDGE(0x00020000),
    APP_WINDOW(0x00040000),
    OVERLAPPED_WINDOW(WINDOW_EDGE.value | CLIENT_EDGE.value),
    PALETTE_WINDOW(WINDOW_EDGE.value | TOOL_WINDOW.value | TOPMOST.value),
    LAYERED(0x00080000),
    NO_INHERIT_LAYOUT(0x00100000),
    NO_REDIRECTION_BITMAP(0x00200000),
    LAYOUT_RTL(0x00400000),
    COMPOSITED(0x02000000),
    NO_ACTIVATE(0x08000000);

    private final int value;

    ExtendedWindowStyle(int value) {
        this.value = value;
    }

    @Override
    public int intValue() {
        return value;
    }

}
