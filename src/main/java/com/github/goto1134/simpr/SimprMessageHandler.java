package com.github.goto1134.simpr;

import com.github.goto1134.simpr.win32.*;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;

import java.util.EnumSet;

/**
 * Registers a win32 window and processes messages from SIMPR program
 */
public class SimprMessageHandler
        implements WindowProcessorCallback {
    public static final String SIMPR_CLIENT_WINDOW = "simprClientWindow";
    private final SimprClient simprClient;
    private final WinUser winUserLib = WinUser.getInstance();
    private final int messageCode;
    private final Win32WindowHandle window;

    /**
     * @param message     Message code will be registered for this window to exchange data with SIMPR
     * @param windowName  Name that will be associated with this window
     * @param simprClient User-defined client for SIMPR simulation
     */
    public SimprMessageHandler(String message, String windowName, SimprClient simprClient) {
        this.simprClient = simprClient;
        winUserLib.RegisterClass(new WNDCLASS(Runtime.getRuntime(winUserLib), this, SIMPR_CLIENT_WINDOW));
        window = winUserLib.CreateWindow(SIMPR_CLIENT_WINDOW, windowName, EnumSet.noneOf(WindowStyle.class), null, null,
                WinBase.getInstance().getProgramInstanceHandle(), null);
        messageCode = winUserLib.RegisterWindowMessage(message);
    }

    @Override
    public Pointer WindowProc(Pointer windowHandle, int message, Pointer wParam, Pointer lParam) {
        if (message != messageCode) {
            return winUserLib.DefWindowProc(new Win32WindowHandle(windowHandle), message, wParam, lParam);
        } else {
            long wParamValue = wParam.address();
            boolean isCondition = wParamValue / 65536 == 0;
            int tableIndex = (int) (wParamValue - (isCondition ? 0 : 65536));
            if (lParam == null) {
                simprClient.onEndStateReached(tableIndex);
                return Pointer.wrap(Runtime.getSystemRuntime(), 1);
            } else {
                long lParamValue = lParam.address();
                int result = isCondition ? simprClient.getConditionValue(tableIndex, (int) lParamValue) ? 1 : 0
                                         : simprClient.performEvent(tableIndex, (int) lParamValue) ? 1 : 0;

                return Pointer.wrap(Runtime.getSystemRuntime(), result);
            }
        }
    }
}
