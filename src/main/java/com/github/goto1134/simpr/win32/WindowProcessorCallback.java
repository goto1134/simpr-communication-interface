package com.github.goto1134.simpr.win32;

import jnr.ffi.*;
import jnr.ffi.Runtime;
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
    default Pointer WindowProc(Pointer windowHandle, int message, Pointer wParam, Pointer lParam) {
        Pointer resultHolder = Memory.allocate(Runtime.getSystemRuntime(), NativeType.UINT);
        resultHolder.putNativeLong(0, WindowProc(windowHandle, message, wParam == null ? 0 : wParam.getNativeLong(0),
                                                 lParam == null ? 0 : lParam.getNativeLong(0)));
        return resultHolder;
    }

    /**
     * @param windowHandle A handle to the window
     * @param message      The message.
     * @param wParam       Additional message information. The contents of this parameter depend on the value of the
     *                     uMsg
     *                     parameter.
     * @param lParam       Additional message information. The contents of this parameter depend on the value of the
     *                     uMsg
     *                     parameter.
     * @return The return value is the result of the message processing and depends on the message sent.
     */
    long WindowProc(Pointer windowHandle, int message, long wParam, long lParam);
}
