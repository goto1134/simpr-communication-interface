package com.github.goto1134.simpr;

import jnr.ffi.Pointer;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;

public final class Win32MenuHandle {
    @ToNativeConverter.ToNative(nativeType = Pointer.class)
    public static Pointer toNative(Win32MenuHandle value, ToNativeContext context) {
        return value != null ? value.pointer : null;
    }

    @FromNativeConverter.FromNative(nativeType = Pointer.class)
    public static Win32MenuHandle fromNative(Pointer value, FromNativeContext context) {
        return value != null ? new Win32MenuHandle(value) : null;
    }

    private final Pointer pointer;

    public Win32MenuHandle(Pointer pointer) {
        this.pointer = pointer;
    }
}