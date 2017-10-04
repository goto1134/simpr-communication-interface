package com.github.goto1134.simpr.win32;

import jnr.ffi.util.EnumMapper;

/**
 * Created by Andrew
 * on 17.09.2017.
 */
enum WindowMessage
        implements EnumMapper.IntegerEnum {
    WM_NULL(0x0000),
    WM_CREATE(0x0001),
    WM_DESTROY(0x0002),
    WM_MOVE(0x0003),
    WM_SIZE(0x0005),

    /**
     * See {@link ActivateMessageStatus} for status values
     */
    WM_ACTIVATE(0x0006),
    WM_SETFOCUS(0x0007),
    WM_KILLFOCUS(0x0008),
    WM_ENABLE(0x000A),
    WM_SETREDRAW(0x000B),
    WM_SETTEXT(0x000C),
    WM_GETTEXT(0x000D),
    WM_GETTEXTLENGTH(0x000E),
    WM_PAINT(0x000F),
    WM_CLOSE(0x0010),
    WM_QUERYENDSESSION(0x0011),
    WM_QUERYOPEN(0x0013),
    WM_ENDSESSION(0x0016),
    WM_QUIT(0x0012),
    WM_ERASEBKGND(0x0014),
    WM_SYSCOLORCHANGE(0x0015),
    WM_SHOWWINDOW(0x0018),
    WM_WININICHANGE(0x001A),
    WM_SETTINGCHANGE(WM_WININICHANGE.value),
    WM_DEVMODECHANGE(0x001B),
    WM_ACTIVATEAPP(0x001C),
    WM_FONTCHANGE(0x001D),
    WM_TIMECHANGE(0x001E),
    WM_CANCELMODE(0x001F),
    WM_SETCURSOR(0x0020),
    WM_MOUSEACTIVATE(0x0021),
    WM_CHILDACTIVATE(0x0022),
    WM_QUEUESYNC(0x0023),

    /**
     * Needs a custom structure to process tagMINMAXINFO
     */
    WM_GETMINMAXINFO(0x0024),
    WM_PAINTICON(0x0026),
    WM_ICONERASEBKGND(0x0027),
    WM_NEXTDLGCTL(0x0028),
    WM_SPOOLERSTATUS(0x002A),
    WM_DRAWITEM(0x002B),
    WM_MEASUREITEM(0x002C),
    WM_DELETEITEM(0x002D),
    WM_VKEYTOITEM(0x002E),
    WM_CHARTOITEM(0x002F),
    WM_SETFONT(0x0030),
    WM_GETFONT(0x0031),
    WM_SETHOTKEY(0x0032),
    WM_GETHOTKEY(0x0033),
    WM_QUERYDRAGICON(0x0037),
    WM_COMPAREITEM(0x0039),
    WM_GETOBJECT(0x003D),
    WM_COMPACTING(0x0041),
    WM_COMMNOTIFY(0x0044),  /* no longer suported */
    WM_WINDOWPOSCHANGING(0x0046),
    WM_WINDOWPOSCHANGED(0x0047),

    WM_POWER(0x0048),;

    private final int value;

    WindowMessage(int value) {this.value = value;}

    @Override
    public int intValue() {
        return this.value;
    }
}
