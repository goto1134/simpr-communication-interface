package com.github.goto1134.simpr.win32;

import jnr.ffi.Address;
import jnr.ffi.LibraryLoader;
import jnr.ffi.Pointer;

/**
 * Created by Andrew
 * on 17.09.2017.
 */
@SuppressWarnings({"DeprecatedIsStillUsed", "deprecation"})
public interface WinBase {

    Address ZERO = Address.valueOf(0);

    static WinBase getInstance() {
        return WinBaseHolder.instance;
    }

    int GetLastError();

    default Win32InstanceHandle getProgramInstanceHandle() {
        return new Win32InstanceHandle(getProgramHandle());
    }

    default Pointer getProgramHandle() {
        return Win32Encoding.isUnicode() ? GetModuleHandleW(ZERO) : GetModuleHandleA(ZERO);
    }

    @Deprecated
    Pointer GetModuleHandleW(Address aString);

    @Deprecated
    Pointer GetModuleHandleA(Address aString);


    class WinBaseHolder {
        private static final WinBase instance = LibraryLoader.create(WinBase.class)
                                                             .failImmediately()
                                                             .library("kernel32")
                                                             .load();
    }
}
