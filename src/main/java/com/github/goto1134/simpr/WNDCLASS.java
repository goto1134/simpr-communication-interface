package com.github.goto1134.simpr;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.util.EnumMapper.IntegerEnum;

/**
 * Created by Andrew
 * on 14.09.2017.
 * todo ДОДЕЛАТЬ
 */
class WNDCLASS
        extends Struct {

    private final Unsigned16 style = new Unsigned16();
    private final Function<WNDPROC> lpfnWndProc = function(WNDPROC.class);
    private final Unsigned16 cbClsExtra = new Unsigned16();
    private final Unsigned16 cbWndExtra = new Unsigned16();
    private final Pointer hInstance = new Pointer();
    private final Pointer hIcon = new Pointer();
    private final Pointer hCursor = new Pointer();
    private final Pointer hbrBackground = new Pointer();
    private final UTFStringRef lpszMenuName = new AsciiStringRef();
    private final UTFStringRef lpszClassName = new AsciiStringRef();

    public WNDCLASS(Runtime runtime) {
        super(runtime);
    }

    /**
     * Class styles
     */
    enum ClassStyle
            implements IntegerEnum {

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
}
