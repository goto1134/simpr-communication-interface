package com.github.goto1134.simpr;

import jnr.ffi.*;
import jnr.ffi.Runtime;
import jnr.ffi.annotations.SaveError;
import jnr.ffi.types.u_int32_t;

/**
 * Created by Andrew
 * on 14.09.2017.
 */
@SaveError
@SuppressWarnings({"DeprecatedIsStillUsed", "deprecation"})
public interface WinUser {
    static WinUser getInstance() {
        return WinUserHolder.instance;
    }

    default Win32WindowHandle CreateWindow(String lpClassName,
                                           String lpWindowName,
                                           int dwStyle,
                                           short x,
                                           short y,
                                           short nWidth,
                                           short nHeight,
                                           Win32WindowHandle hWndParent,
                                           Win32MenuHandle hMenu,
                                           Win32InstanceHandle hInstance,
                                           Pointer lpParam) {
        return CreateWindowEx(ExtendedWindowStyle.RIGHTSCROLLBAR, lpClassName, lpWindowName, dwStyle, x, y, nWidth,
                              nHeight, hWndParent, hMenu, hInstance, lpParam);
    }

    /**
     * @param dwExStyle    The extended window style of the window being created. For a list of possible values,see
     *                     {@link ExtendedWindowStyle}
     * @param lpClassName  A null-terminated string or a class atom created by a previous call to the
     *                     {@link #RegisterClass(WNDCLASS)}
     *                     or RegisterClassEx function. The atom must be in the low-order word of lpClassName; the
     *                     high-order word must be zero. If lpClassName is a string, it specifies the window class
     *                     name. The class name can be any name registered with RegisterClass or RegisterClassEx,
     *                     provided that the module that registers the class is also the module that creates the
     *                     window. The class name can also be any of the predefined system class names.
     * @param lpWindowName The window name. If the window style specifies a title bar, the window title pointed to by
     *                     lpWindowName is displayed in the title bar. When using CreateWindow to create controls,
     *                     such as buttons, check boxes, and static controls, use lpWindowName to specify the text of
     *                     the control. When creating a static control with the SS_ICON style, use lpWindowName to
     *                     specify the icon name or identifier. To specify an identifier, use the syntax "#num
     * @param dwStyle
     * @param x
     * @param y
     * @param nWidth
     * @param nHeight
     * @param hWndParent
     * @param hMenu
     * @param hInstance
     * @param lpParam
     * @return
     */
    default Win32WindowHandle CreateWindowEx(ExtendedWindowStyle dwExStyle,
                                             String lpClassName,
                                             String lpWindowName,
                                             int dwStyle,
                                             short x,
                                             short y,
                                             short nWidth,
                                             short nHeight,
                                             Win32WindowHandle hWndParent,
                                             Win32MenuHandle hMenu,
                                             Win32InstanceHandle hInstance,
                                             Pointer lpParam) {
        return Win32Encoding.isUnicode() ? CreateWindowExW(dwExStyle, lpClassName, lpWindowName, dwStyle, x, y, nWidth,
                                                           nHeight, hWndParent, hMenu, hInstance, lpParam)
                                         : CreateWindowExA(dwExStyle, lpClassName, lpWindowName, dwStyle, x, y, nWidth,
                                                           nHeight, hWndParent, hMenu, hInstance, lpParam);
    }

    @Deprecated
    Win32WindowHandle CreateWindowExW(ExtendedWindowStyle dwExStyle,
                                      String lpClassName,
                                      String lpWindowName,
                                      int dwStyle,
                                      short x,
                                      short y,
                                      short nWidth,
                                      short nHeight,
                                      Win32WindowHandle hWndParent,
                                      Win32MenuHandle hMenu,
                                      Win32InstanceHandle hInstance,
                                      Pointer lpParam);

    @Deprecated
    Win32WindowHandle CreateWindowExA(ExtendedWindowStyle dwExStyle,
                                      String lpClassName,
                                      String lpWindowName,
                                      int dwStyle,
                                      short x,
                                      short y,
                                      short nWidth,
                                      short nHeight,
                                      Win32WindowHandle hWndParent,
                                      Win32MenuHandle hMenu,
                                      Win32InstanceHandle hInstance,
                                      Pointer lpParam);

    @Deprecated
    default Win32WindowHandle CreateWindowW(String lpClassName,
                                            String lpWindowName,
                                            int dwStyle,
                                            short x,
                                            short y,
                                            short nWidth,
                                            short nHeight,
                                            Win32WindowHandle hWndParent,
                                            Win32MenuHandle hMenu,
                                            Win32InstanceHandle hInstance,
                                            Pointer lpParam) {
        return CreateWindowExW(ExtendedWindowStyle.RIGHTSCROLLBAR, lpClassName, lpWindowName, dwStyle, x, y, nWidth,
                               nHeight, hWndParent, hMenu, hInstance, lpParam);
    }

    @Deprecated
    default Win32WindowHandle CreateWindowA(String lpClassName,
                                            String lpWindowName,
                                            int dwStyle,
                                            short x,
                                            short y,
                                            short nWidth,
                                            short nHeight,
                                            Win32WindowHandle hWndParent,
                                            Win32MenuHandle hMenu,
                                            Win32InstanceHandle hInstance,
                                            Pointer lpParam) {
        return CreateWindowExA(ExtendedWindowStyle.RIGHTSCROLLBAR, lpClassName, lpWindowName, dwStyle, x, y, nWidth,
                               nHeight, hWndParent, hMenu, hInstance, lpParam);
    }

    /**
     * @param lpWndClass A pointer to a WNDCLASS structure. You must fill the structure with the appropriate class
     *                   attributes before passing it to the function.
     * @return If the function succeeds, the return value is a class atom that uniquely identifies the class being
     * registered. This atom can only be used by the CreateWindow, CreateWindowEx, GetClassInfo, GetClassInfoEx,
     * FindWindow, FindWindowEx, and UnregisterClass functions and the IActiveIMMap::FilterClientWindows method.
     * If the function fails, the return value is zero. To get extended error information, call GetLastError.
     */
    default short RegisterClass(WNDCLASS lpWndClass)
            throws Win32Exception {
        short result = Win32Encoding.isUnicode() ? RegisterClassW(lpWndClass) : RegisterClassA(lpWndClass);
        if (result == 0) {
            throw new Win32Exception("Error occurred " + WinBase.getInstance()
                                                                .GetLastError());
        }
        return result;
    }

    @Deprecated
    short RegisterClassW(WNDCLASS lpWndClass);

    @Deprecated
    short RegisterClassA(WNDCLASS lpWndClass);

    /**
     * @param lpPrevWndFunc The previous window procedure. If this value is obtained by calling the GetWindowLong
     *                      function with the nIndex parameter set to GWL_WNDPROC or DWL_DLGPROC, it is actually
     *                      either the address of a window or dialog box procedure, or a special internal value
     *                      meaningful only to CallWindowProc.
     * @param hWnd          A handle to the window procedure to receive the message.
     * @param Msg           The message.
     * @param wParam        Additional message-specific information. The contents of this parameter depend on the
     *                      value of the Msg parameter.
     * @param lParam        Additional message-specific information. The contents of this parameter depend on the
     *                      value of the Msg parameter.
     * @return The return value specifies the result of the message processing and depends on the message sent.
     */
    default long CallWindowProc(WindowProcessorCallback lpPrevWndFunc,
                                Win32WindowHandle hWnd,
                                int Msg,
                                long wParam,
                                long lParam) {
        Pointer wParamWrap = Memory.allocate(Runtime.getSystemRuntime(), NativeType.ULONG);
        wParamWrap.putNativeLong(0, wParam);
        Pointer lParamWrap = Memory.allocate(Runtime.getSystemRuntime(), NativeType.ULONG);
        lParamWrap.putNativeLong(0, lParam);
        Pointer result = CallWindowProc(lpPrevWndFunc, hWnd, Msg, wParamWrap, lParamWrap);
        return result.getNativeLong(0);
    }


    /**
     * @param lpPrevWndFunc The previous window procedure. If this value is obtained by calling the GetWindowLong
     *                      function with the nIndex parameter set to GWL_WNDPROC or DWL_DLGPROC, it is actually
     *                      either the address of a window or dialog box procedure, or a special internal value
     *                      meaningful only to CallWindowProc.
     * @param hWnd          A handle to the window procedure to receive the message.
     * @param Msg           The message.
     * @param wParam        Additional message-specific information. The contents of this parameter depend on the
     *                      value of the Msg parameter.
     * @param lParam        Additional message-specific information. The contents of this parameter depend on the
     *                      value of the Msg parameter.
     * @return The return value specifies the result of the message processing and depends on the message sent.
     */
    @Deprecated
    default Pointer CallWindowProc(WindowProcessorCallback lpPrevWndFunc,
                                   Win32WindowHandle hWnd,
                                   int Msg,
                                   Pointer wParam,
                                   Pointer lParam) {
        return Win32Encoding.isUnicode() ? CallWindowProcW(lpPrevWndFunc, hWnd, Msg, wParam, lParam)
                                         : CallWindowProcA(lpPrevWndFunc, hWnd, Msg, wParam, lParam);
    }

    /**
     * @param lpPrevWndFunc The previous window procedure. If this value is obtained by calling the GetWindowLong
     *                      function with the nIndex parameter set to GWL_WNDPROC or DWL_DLGPROC, it is actually
     *                      either the address of a window or dialog box procedure, or a special internal value
     *                      meaningful only to CallWindowProc.
     * @param hWnd          A handle to the window procedure to receive the message.
     * @param Msg           The message.
     * @param wParam        Additional message-specific information. The contents of this parameter depend on the
     *                      value of the Msg parameter.
     * @param lParam        Additional message-specific information. The contents of this parameter depend on the
     *                      value of the Msg parameter.
     * @return The return value specifies the result of the message processing and depends on the message sent.
     */
    @Deprecated
    Pointer CallWindowProcW(WindowProcessorCallback lpPrevWndFunc,
                            Win32WindowHandle hWnd,
                            int Msg,
                            Pointer wParam,
                            Pointer lParam);

    /**
     * @param lpPrevWndFunc The previous window procedure. If this value is obtained by calling the GetWindowLong
     *                      function with the nIndex parameter set to GWL_WNDPROC or DWL_DLGPROC, it is actually
     *                      either the address of a window or dialog box procedure, or a special internal value
     *                      meaningful only to CallWindowProc.
     * @param hWnd          A handle to the window procedure to receive the message.
     * @param Msg           The message.
     * @param wParam        Additional message-specific information. The contents of this parameter depend on the
     *                      value of the Msg parameter.
     * @param lParam        Additional message-specific information. The contents of this parameter depend on the
     *                      value of the Msg parameter.
     * @return The return value specifies the result of the message processing and depends on the message sent.
     */
    @Deprecated
    Pointer CallWindowProcA(WindowProcessorCallback lpPrevWndFunc,
                            Win32WindowHandle hWnd,
                            int Msg,
                            Pointer wParam,
                            Pointer lParam);


    /**
     * @param message The message to be displayed. If the string consists of more than one line, you can separate
     *                the lines using a carriage return and/or linefeed character between each line.
     * @param title   The dialog box title. If this parameter is NULL, the default title is Error.
     * @param uType   The contents and behavior of the dialog box. This parameter can be a combination of flags
     *                from the following groups of flags.
     * @return If a message box has a Cancel button, the function returns the IDCANCEL value if either the ESC key is
     * pressed or the Cancel button is selected. If the message box has no Cancel button, pressing ESC has no effect.
     * If the function fails, the return value is zero. To get extended error information, call GetLastError.
     */
    default int showMessage(String message, String title, @u_int32_t long uType)
            throws Win32Exception {return showMessage(null, message, title, uType);}

    /**
     * @param hWnd    A handle to the owner window of the message box to be created. If this parameter is NULL, the
     *                message box has no owner window.
     * @param message The message to be displayed. If the string consists of more than one line, you can separate
     *                the lines using a carriage return and/or linefeed character between each line.
     * @param title   The dialog box title. If this parameter is NULL, the default title is Error.
     * @param uType   The contents and behavior of the dialog box. This parameter can be a combination of flags
     *                from the following groups of flags.
     * @return If a message box has a Cancel button, the function returns the IDCANCEL value if either the ESC key is
     * pressed or the Cancel button is selected. If the message box has no Cancel button, pressing ESC has no effect.
     * If the function fails, the return value is zero. To get extended error information, call GetLastError.
     */
    default int showMessage(Win32WindowHandle hWnd, String message, String title, @u_int32_t long uType) {
        int result = Win32Encoding.isUnicode() ? MessageBoxW(hWnd, message, title, uType)
                                               : MessageBoxA(hWnd, message, title, uType);
        if (result == 0) {
            throw new Win32Exception("MessageBox display error " + WinBase.getInstance()
                                                                          .GetLastError());
        }
        return result;
    }

    /**
     * @param hWnd      A handle to the owner window of the message box to be created. If this parameter is NULL, the
     *                  message box has no owner window.
     * @param lpText    The message to be displayed. If the string consists of more than one line, you can separate
     *                  the lines using a carriage return and/or linefeed character between each line.
     * @param lpCaption The dialog box title. If this parameter is NULL, the default title is Error.
     * @param uType     The contents and behavior of the dialog box. This parameter can be a combination of flags
     *                  from the following groups of flags.
     * @return If a message box has a Cancel button, the function returns the IDCANCEL value if either the ESC key is
     * pressed or the Cancel button is selected. If the message box has no Cancel button, pressing ESC has no effect.
     * If the function fails, the return value is zero. To get extended error information, call GetLastError.
     */
    @Deprecated
    int MessageBoxW(Win32WindowHandle hWnd, String lpText, String lpCaption, @u_int32_t long uType);

    /**
     * @param hWnd      A handle to the owner window of the message box to be created. If this parameter is NULL, the
     *                  message box has no owner window.
     * @param lpText    The message to be displayed. If the string consists of more than one line, you can separate
     *                  the lines using a carriage return and/or linefeed character between each line.
     * @param lpCaption The dialog box title. If this parameter is NULL, the default title is Error.
     * @param uType     The contents and behavior of the dialog box. This parameter can be a combination of flags
     *                  from the following groups of flags.
     * @return If a message box has a Cancel button, the function returns the IDCANCEL value if either the ESC key is
     * pressed or the Cancel button is selected. If the message box has no Cancel button, pressing ESC has no effect.
     * If the function fails, the return value is zero. To get extended error information, call GetLastError.
     */
    @Deprecated
    int MessageBoxA(Win32WindowHandle hWnd, String lpText, String lpCaption, @u_int32_t long uType);

    class WinUserHolder {
        private static final WinUser instance = LibraryLoader.create(WinUser.class)
                                                             .failImmediately()
                                                             .library("user32")
                                                             .load();
    }
}

