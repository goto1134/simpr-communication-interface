package com.github.goto1134.simpr.win32;

import jnr.ffi.*;
import jnr.ffi.Runtime;
import jnr.ffi.annotations.In;
import jnr.ffi.annotations.SaveError;
import jnr.ffi.types.u_int32_t;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Andrew
 * on 14.09.2017.
 */
@SaveError
@SuppressWarnings({"DeprecatedIsStillUsed", "deprecation"})
public interface WinUser {

    Set<ExtendedWindowStyle> DEFAULT_EXTENDED_WINDOW_STYLE = EnumSet.of(ExtendedWindowStyle.RIGHT_SCROLLBAR);
    int CW_USE_DEFAULT = 0x80000000;


    static WinUser getInstance() {
        return WinUserHolder.instance;
    }

    /**
     * @param className      A null-terminated string or a class atom created by a previous call to the
     *                       {@link #RegisterClass} or {@link #RegisterClassEx} function. The atom must be in the
     *                       low-order word of ClassName; the
     *                       high-order word must be zero. If ClassName is a string, it specifies the window class
     *                       name. The class name can be any name registered with RegisterClass or RegisterClassEx,
     *                       provided that the module that registers the class is also the module that creates the
     *                       window. The class name can also be any of the predefined system class names. For a list of
     *                       system class names, see the Remarks section.
     * @param windowName     The window name. If the window style specifies a title bar, the window title pointed to
     *                       by lpWindowName is displayed in the title bar. When using CreateWindow to create
     *                       controls, such as buttons, check boxes, and static controls, use lpWindowName to specify
     *                       the text of the control. When creating a static control with the SS_ICON style, use
     *                       lpWindowName to specify the icon name or identifier. To specify an identifier, use the
     *                       syntax "#num".
     * @param style          The style of the window being created. This parameter can be a combination of the window
     *                       style values, plus the control styles indicated in the Remarks section.
     * @param parentWindow
     * @param menu
     * @param instanceHandle
     * @param creationParam
     * @return
     */
    default Win32WindowHandle CreateWindow(@In @Nullable String className,
                                           @In @Nullable String windowName,
                                           @In @u_int32_t Set<WindowStyle> style,
                                           @In @Nullable Win32WindowHandle parentWindow,
                                           @In @Nullable Win32MenuHandle menu,
                                           @In @Nullable Win32InstanceHandle instanceHandle,
                                           @In @Nullable Pointer creationParam) {
        return CreateWindow(className, windowName, style, CW_USE_DEFAULT, CW_USE_DEFAULT, CW_USE_DEFAULT,
                            CW_USE_DEFAULT, parentWindow, menu, instanceHandle, creationParam);
    }

    /**
     * @param className      A null-terminated string or a class atom created by a previous call to the
     *                       {@link #RegisterClass} or {@link #RegisterClassEx} function. The atom must be in the
     *                       low-order word of ClassName; the
     *                       high-order word must be zero. If ClassName is a string, it specifies the window class
     *                       name. The class name can be any name registered with RegisterClass or RegisterClassEx,
     *                       provided that the module that registers the class is also the module that creates the
     *                       window. The class name can also be any of the predefined system class names. For a list of
     *                       system class names, see the Remarks section.
     * @param windowName     The window name. If the window style specifies a title bar, the window title pointed to
     *                       by lpWindowName is displayed in the title bar. When using CreateWindow to create
     *                       controls, such as buttons, check boxes, and static controls, use lpWindowName to specify
     *                       the text of the control. When creating a static control with the SS_ICON style, use
     *                       lpWindowName to specify the icon name or identifier. To specify an identifier, use the
     *                       syntax "#num".
     * @param style          The style of the window being created. This parameter can be a combination of the window
     *                       style values, plus the control styles indicated in the Remarks section.
     * @param x              The initial horizontal position of the window. For an overlapped or pop-up window, the x
     *                       parameter is the initial x-coordinate of the window's upper-left corner, in screen
     *                       coordinates. For a child window, x is the x-coordinate of the upper-left corner of the
     *                       window relative to the upper-left corner of the parent window's client area. If this
     *                       parameter is set to CW_USEDEFAULT, the system selects the default position for the
     *                       window's upper-left corner and ignores the y parameter. CW_USEDEFAULT is valid only for
     *                       overlapped windows; if it is specified for a pop-up or child window, the x and y
     *                       parameters are set to zero.
     * @param y              The initial vertical position of the window. For an overlapped or pop-up window, the y
     *                       parameter is the initial y-coordinate of the window's upper-left corner, in screen
     *                       coordinates. For a child window, y is the initial y-coordinate of the upper-left corner
     *                       of the child window relative to the upper-left corner of the parent window's client area
     *                       . For a list box, y is the initial y-coordinate of the upper-left corner of the list
     *                       box's client area relative to the upper-left corner of the parent window's client area.
     *                       If an overlapped window is created with the WS_VISIBLE style bit set and the x parameter
     *                       is set to CW_USEDEFAULT, then the y parameter determines how the window is shown. If the
     *                       y parameter is CW_USEDEFAULT, then the window manager calls ShowWindow with the SW_SHOW
     *                       flag after the window has been created. If the y parameter is some other value, then the
     *                       window manager calls ShowWindow with that value as the nCmdShow parameter.
     * @param width
     * @param height
     * @param parentWindow
     * @param menu
     * @param instanceHandle
     * @param creationParam
     * @return
     */
    default Win32WindowHandle CreateWindow(@In @Nullable String className,
                                           @In @Nullable String windowName,
                                           @In @u_int32_t Set<WindowStyle> style,
                                           @In int x,
                                           @In int y,
                                           @In int width,
                                           @In int height,
                                           @In @Nullable Win32WindowHandle parentWindow,
                                           @In @Nullable Win32MenuHandle menu,
                                           @In @Nullable Win32InstanceHandle instanceHandle,
                                           @In @Nullable Pointer creationParam) {
        return CreateWindowEx(DEFAULT_EXTENDED_WINDOW_STYLE, className, windowName, style, x, y, width,
                              height, parentWindow, menu, instanceHandle, creationParam);
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
    default Win32WindowHandle CreateWindowEx(Set<ExtendedWindowStyle> dwExStyle,
                                             @In @Nullable String lpClassName,
                                             @In @Nullable String lpWindowName,
                                             @In @u_int32_t Set<WindowStyle> dwStyle,
                                             @In int x,
                                             @In int y,
                                             @In int nWidth,
                                             @In int nHeight,
                                             @In @Nullable Win32WindowHandle hWndParent,
                                             @In @Nullable Win32MenuHandle hMenu,
                                             @In @Nullable Win32InstanceHandle hInstance,
                                             @In @Nullable Pointer lpParam) {
        return Win32Encoding.isUnicode() ? CreateWindowExW(dwExStyle, lpClassName, lpWindowName, dwStyle, x, y, nWidth,
                                                           nHeight, hWndParent, hMenu, hInstance, lpParam)
                                         : CreateWindowExA(dwExStyle, lpClassName, lpWindowName, dwStyle, x, y, nWidth,
                                                           nHeight, hWndParent, hMenu, hInstance, lpParam);
    }

    @Deprecated
    Win32WindowHandle CreateWindowExW(Set<ExtendedWindowStyle> dwExStyle,
                                      @In @Nullable String lpClassName,
                                      @In @Nullable String lpWindowName,
                                      @In @u_int32_t Set<WindowStyle> dwStyle,
                                      @In int x,
                                      @In int y,
                                      @In int nWidth,
                                      @In int nHeight,
                                      @In @Nullable Win32WindowHandle hWndParent,
                                      @In @Nullable Win32MenuHandle hMenu,
                                      @In @Nullable Win32InstanceHandle hInstance,
                                      @In @Nullable Pointer lpParam);

    @Deprecated
    Win32WindowHandle CreateWindowExA(Set<ExtendedWindowStyle> dwExStyle,
                                      @In @Nullable String lpClassName,
                                      @In @Nullable String lpWindowName,
                                      @In @u_int32_t Set<WindowStyle> dwStyle,
                                      @In int x,
                                      @In int y,
                                      @In int nWidth,
                                      @In int nHeight,
                                      @In @Nullable Win32WindowHandle hWndParent,
                                      @In @Nullable Win32MenuHandle hMenu,
                                      @In @Nullable Win32InstanceHandle hInstance,
                                      @In @Nullable Pointer lpParam);

    @Deprecated
    default Win32WindowHandle CreateWindowW(@In @Nullable String lpClassName,
                                            @In @Nullable String lpWindowName,
                                            @In @u_int32_t Set<WindowStyle> dwStyle,
                                            @In int x,
                                            @In int y,
                                            @In int nWidth,
                                            @In int nHeight,
                                            @In @Nullable Win32WindowHandle hWndParent,
                                            @In @Nullable Win32MenuHandle hMenu,
                                            @In @Nullable Win32InstanceHandle hInstance,
                                            @In @Nullable Pointer lpParam) {
        return CreateWindowExW(DEFAULT_EXTENDED_WINDOW_STYLE, lpClassName, lpWindowName, dwStyle, x, y, nWidth,
                               nHeight, hWndParent, hMenu, hInstance, lpParam);
    }

    @Deprecated
    default Win32WindowHandle CreateWindowA(@In @Nullable String lpClassName,
                                            @In @Nullable String lpWindowName,
                                            @In @u_int32_t Set<WindowStyle> dwStyle,
                                            @In int x,
                                            @In int y,
                                            @In int nWidth,
                                            @In int nHeight,
                                            @In @Nullable Win32WindowHandle hWndParent,
                                            @In @Nullable Win32MenuHandle hMenu,
                                            @In @Nullable Win32InstanceHandle hInstance,
                                            @In @Nullable Pointer lpParam) {
        return CreateWindowExA(DEFAULT_EXTENDED_WINDOW_STYLE, lpClassName, lpWindowName, dwStyle, x, y, nWidth,
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

    default Pointer DefWindowProc(@In Win32WindowHandle hWnd, @In int Msg,
                                  @In Pointer wParam,
                                  @In Pointer lParam) {

        return Win32Encoding.isUnicode() ? DefWindowProcW(hWnd, Msg, wParam, lParam)
                                         : DefWindowProcA(hWnd, Msg, wParam, lParam);
    }

    @Deprecated
    Pointer DefWindowProcW(@In Win32WindowHandle hWnd, @In int Msg, @In Pointer wParam, @In Pointer lParam);

    @Deprecated
    Pointer DefWindowProcA(@In Win32WindowHandle hWnd, @In int Msg, @In Pointer wParam, @In Pointer lParam);

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
    default int showMessage(String message, String title, @u_int32_t Set<MessageBoxFlags> uType)
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
    default int showMessage(Win32WindowHandle hWnd,
                            String message,
                            String title,
                            @u_int32_t Set<MessageBoxFlags> uType) {
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
    int MessageBoxW(Win32WindowHandle hWnd, String lpText, String lpCaption, @u_int32_t Set<MessageBoxFlags> uType);

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
    int MessageBoxA(Win32WindowHandle hWnd, String lpText, String lpCaption, @u_int32_t Set<MessageBoxFlags> uType);

    /**
     * @param message The message to be registered.
     * @return if the message is successfully registered, the return value is a message identifier in the range 0xC000
     * through 0xFFFF.
     */
    default int RegisterWindowMessage(String message) {
        int result = Win32Encoding.isUnicode() ? RegisterWindowMessageW(message) : RegisterWindowMessageA(message);
        if (result == 0) {
            throw new Win32Exception("Could not register message " + message + " with error " + WinBase.getInstance()
                                                                                                       .GetLastError());
        }
        return result;
    }

    /**
     * @param message The message to be registered.
     * @return if the message is successfully registered, the return value is a message identifier in the range 0xC000
     * through 0xFFFF.
     * If the function fails, the return value is zero. To get extended error information, call GetLastError.
     */
    @Deprecated
    int RegisterWindowMessageW(String message);

    /**
     * @param message The message to be registered.
     * @return if the message is successfully registered, the return value is a message identifier in the range 0xC000
     * through 0xFFFF.
     * If the function fails, the return value is zero. To get extended error information, call GetLastError.
     */
    @Deprecated
    int RegisterWindowMessageA(String message);


    class WinUserHolder {
        private static final WinUser instance = LibraryLoader.create(WinUser.class)
                                                             .failImmediately()
                                                             .library("user32")
                                                             .load();
    }
}

