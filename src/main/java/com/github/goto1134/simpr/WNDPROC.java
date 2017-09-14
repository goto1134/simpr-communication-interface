package com.github.goto1134.simpr;

import jnr.ffi.*;
import jnr.ffi.Runtime;
import jnr.ffi.annotations.Delegate;

/**
 * Created by Andrew
 * on 14.09.2017.
 */
public interface WNDPROC {
    /**
     * @param hwnd   A handle to the window
     * @param uMsg   The message.
     * @param wParam Additional message information. The contents of this parameter depend on the value of the uMsg
     *               parameter.
     * @param lParam Additional message information. The contents of this parameter depend on the value of the uMsg
     *               parameter.
     * @return The return value is the result of the message processing and depends on the message sent.
     */

    @Delegate(convention = CallingConvention.STDCALL)
    default Pointer WindowProc(Pointer hwnd, int uMsg, Pointer wParam, Pointer lParam) {
        Pointer resultHolder = Memory.allocate(Runtime.getSystemRuntime(), NativeType.UINT);
        resultHolder.putNativeLong(0, WindowProc(hwnd, uMsg, wParam.getNativeLong(0), lParam.getNativeLong(0)));
        return resultHolder;
    }

    /**
     * @param hwnd   A handle to the window
     * @param uMsg   The message.
     * @param wParam Additional message information. The contents of this parameter depend on the value of the uMsg
     *               parameter.
     * @param lParam Additional message information. The contents of this parameter depend on the value of the uMsg
     *               parameter.
     * @return The return value is the result of the message processing and depends on the message sent.
     */
    long WindowProc(Pointer hwnd, int uMsg, long wParam, long lParam);
}
