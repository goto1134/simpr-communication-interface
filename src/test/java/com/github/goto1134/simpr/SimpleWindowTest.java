package com.github.goto1134.simpr;

import jnr.ffi.LibraryLoader;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import org.junit.Before;
import org.junit.Test;

import java.util.EnumSet;

/**
 * Created by Andrew
 * on 16.09.2017.
 */
public class SimpleWindowTest {
    WinUser winUser;

    @Before
    public void setUp()
            throws Exception {
        winUser = LibraryLoader.create(WinUser.class)
                               .failImmediately()
                               .library("user32")
                               .load();
    }

    @Test
    public void name()
            throws Exception {
        WindowProcessorCallback wndproc = new WindowProcessorCallback() {
            @Override
            public long WindowProc(Pointer windowHandle, int message, long wParam, long lParam) {
                System.out.println("Received something" + message);
                return 0;
            }
        };
        String myClass = "MyClass";
        WNDCLASS wndclass = new WNDCLASS(Runtime.getRuntime(winUser), wndproc, myClass);
        winUser.RegisterClass(wndclass);
        Win32WindowHandle window =
                winUser.CreateWindow(myClass, "window", EnumSet.noneOf(WindowStyle.class), null, null,
                                     WinBase.getInstance()
                                            .getProgramInstanceHandle(), null);
    }
}
