package com.github.goto1134.simpr.win32;

import jnr.ffi.Pointer;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;

public final class Win32WindowHandle {
    @ToNativeConverter.ToNative(nativeType = Pointer.class)
    public static Pointer toNative(Win32WindowHandle value, ToNativeContext context) {
        return value != null ? value.pointer : null;
    }

    @FromNativeConverter.FromNative(nativeType = Pointer.class)
    public static Win32WindowHandle fromNative(Pointer value, FromNativeContext context) {
        return value != null ? new Win32WindowHandle(value) : null;
    }

    private final Pointer pointer;

    public Win32WindowHandle(Pointer pointer) {
        this.pointer = pointer;
    }
}