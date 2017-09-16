package com.github.goto1134.simpr.win32;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;

/**
 * Created by Andrew
 * on 14.09.2017.
 */
@SuppressWarnings({"DeprecatedIsStillUsed", "deprecation"})
public class WNDCLASS
        extends Struct {

    public static final int MAX_CLASS_NAME_LENGTH = 256;
    /**
     * The class style(s). This member can be any combination of the {@link ClassStyle}.
     */
    private final Unsigned32 style = new Unsigned32();
    /**
     * A pointer to the window procedure. You must use the {@link WinUser#CallWindowProc} function to call the window
     * procedure. For more information, see {@link WindowProcessorCallback}.
     */
    private final Function<WindowProcessorCallback> lpfnWndProc = function(WindowProcessorCallback.class);
    /**
     * The number of extra bytes to allocate following the window-class structure. The system initializes the bytes
     * to zero.
     */
    private final Unsigned32 cbClsExtra = new Unsigned32();
    /**
     * The number of extra bytes to allocate following the window instance. The system initializes the bytes to zero.
     * If an application uses {@link WNDCLASS} to register a dialog box created by using the CLASS directive in the
     * resource file, it must set this member to DLGWINDOWEXTRA.
     */
    private final Unsigned32 cbWndExtra = new Unsigned32();
    /**
     * A handle to the instance that contains the window procedure for the class.
     */
    private final Pointer hInstance = new Pointer();
    /**
     * A handle to the class icon. This member must be a handle to an icon resource. If this member is NULL, the
     * system provides a default icon.
     */
    private final Pointer hIcon = new Pointer();
    /**
     * A handle to the class cursor. This member must be a handle to a cursor resource. If this member is NULL, an
     * application must explicitly set the cursor shape whenever the mouse moves into the application's window.
     */
    private final Pointer hCursor = new Pointer();
    /**
     * A handle to the class background brush. This member can be a handle to the physical brush to be used for
     * painting the background, or it can be a color value. A color value must be one of the following standard
     * system colors (the value 1 must be added to the chosen color). If a color value is given, you must convert it
     * to one of the following HBRUSH types:
     * <ul>
     * <li>COLOR_ACTIVEBORDER</li>
     * <li>COLOR_ACTIVECAPTION</li>
     * <li>COLOR_APPWORKSPACE</li>
     * <li>COLOR_BACKGROUND</li>
     * <li>COLOR_BTNFACE</li>
     * <li>COLOR_BTNSHADOW</li>
     * <li>COLOR_BTNTEXT</li>
     * <li>COLOR_CAPTIONTEXT</li>
     * <li>COLOR_GRAYTEXT</li>
     * <li>COLOR_HIGHLIGHT</li>
     * <li>COLOR_HIGHLIGHTTEXT</li>
     * <li>COLOR_INACTIVEBORDER</li>
     * <li>COLOR_INACTIVECAPTION</li>
     * <li>COLOR_MENU</li>
     * <li>COLOR_MENUTEXT</li>
     * <li>COLOR_SCROLLBAR</li>
     * <li>COLOR_WINDOW</li>
     * <li>COLOR_WINDOWFRAME</li>
     * <li>COLOR_WINDOWTEXT</li>
     * </ul>
     * The system automatically deletes class background brushes when the class is unregistered by using
     * UnregisterClass. An application should not delete these brushes.
     * When this member is NULL, an application must paint its own background whenever it is requested to paint in
     * its client area. To determine whether the background must be painted, an application can either process the
     * WM_ERASEBKGND message or test the fErase member of the PAINTSTRUCT structure filled by the BeginPaint function.
     */
    private final Pointer hbrBackground = new Pointer();
    /**
     * The resource name of the class menu, as the name appears in the resource file. If you use an integer to
     * identify the menu, use the MAKEINTRESOURCE macro. If this member is NULL, windows belonging to this class have
     * no default menu.
     */
    private final UTFStringRef lpszMenuName = new AsciiStringRef();
    /**
     * A pointer to a null-terminated string or is an atom. If this parameter is an atom, it must be a class atom
     * created by a previous call to the {@link WinUser#RegisterClass} or {@link WinUser#RegisterClassEx} function.
     * The atom must be in the low-order word of {@link #lpszClassName}; the high-order word must be zero.
     * If {@link #lpszClassName} is a string, it specifies the window class name. The class name can be any name
     * registered with {@link WinUser#RegisterClass} or {@link WinUser#RegisterClassEx}, or any of the predefined
     * control-class names. The maximum length for {@link #lpszClassName} is {@link #MAX_CLASS_NAME_LENGTH}. If
     * {@link #lpszClassName} is greater than the maximum length, the {@link WinUser#RegisterClass} function will fail.
     */
    private final UTFStringRef lpszClassName = new AsciiStringRef(MAX_CLASS_NAME_LENGTH);

    public WNDCLASS(Runtime runtime, WindowProcessorCallback wndProc, java.lang.String className) {
        this(runtime);
        lpfnWndProc.set(wndProc);
        lpszClassName.set(className);
        hInstance.set(WinBase.getInstance()
                             .getProgramHandle());
    }

    /**
     * Do not use manually
     *
     * @param runtime
     */
    @Deprecated
    public WNDCLASS(Runtime runtime) {super(runtime);}
}
