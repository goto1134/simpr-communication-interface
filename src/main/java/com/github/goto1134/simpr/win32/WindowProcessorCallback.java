package com.github.goto1134.simpr.win32;

import jnr.ffi.CallingConvention;
import jnr.ffi.Pointer;
import jnr.ffi.annotations.Delegate;

/**
 * Created by Andrew
 * on 14.09.2017.
 */
public interface WindowProcessorCallback {
    /**
     * @param windowHandle A handle to the window
     * @param message      The message.
     * @param wParam       Additional message information. The contents of this parameter depend on the value of the
     *                     message
     *                     parameter.
     * @param lParam       Additional message information. The contents of this parameter depend on the value of the
     *                     message
     *                     parameter.
     * @return The return value is the result of the message processing and depends on the message sent.
     */

    @Delegate(convention = CallingConvention.STDCALL)
    Pointer WindowProc(Pointer windowHandle, int message, Pointer wParam, Pointer lParam);
}
