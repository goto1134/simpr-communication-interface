package com.github.goto1134.simpr;

import jnr.ffi.Pointer;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;

public final class Win32Handle {
    @ToNativeConverter.ToNative(nativeType = jnr.ffi.Pointer.class)
    public static Pointer toNative(Win32Handle value, ToNativeContext context) {
        return value != null ? value.pointer : null;
    }

    @FromNativeConverter.FromNative(nativeType = jnr.ffi.Pointer.class)
    public static Win32Handle fromNative(Pointer value, FromNativeContext context) {
        return value != null ? new Win32Handle(value) : null;
    }

    private final Pointer pointer;

    public Win32Handle(Pointer pointer) {
        this.pointer = pointer;
    }
}