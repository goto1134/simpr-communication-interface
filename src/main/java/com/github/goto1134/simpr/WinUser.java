package com.github.goto1134.simpr;

import jnr.ffi.Memory;
import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;

/**
 * Created by Andrew
 * on 14.09.2017.
 */
interface WinUser {

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
        return CreateWindowExW(ExtendedWindowStyle.RIGHTSCROLLBAR, lpClassName, lpWindowName, dwStyle, x, y, nWidth,
                               nHeight, hWndParent, hMenu, hInstance, lpParam);
    }

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

    /**
     * @param dwExStyle    The extended window style of the window being created. For a list of possible values,see
     *                     {@link ExtendedWindowStyle}
     * @param lpClassName  A null-terminated string or a class atom created by a previous call to the RegisterClass
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

    default short RegisterClass() {
        return Win32Encoding.isUnicode() ? RegisterClassW() : RegisterClassA();
    }

    short RegisterClassW();

    short RegisterClassA();

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
    default long CallWindowProc(WNDPROC lpPrevWndFunc, Win32WindowHandle hWnd, int Msg, long wParam, long lParam) {
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
    default Pointer CallWindowProc(WNDPROC lpPrevWndFunc,
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
    Pointer CallWindowProcW(WNDPROC lpPrevWndFunc, Win32WindowHandle hWnd, int Msg, Pointer wParam, Pointer lParam);

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
    Pointer CallWindowProcA(WNDPROC lpPrevWndFunc, Win32WindowHandle hWnd, int Msg, Pointer wParam, Pointer lParam);

}

