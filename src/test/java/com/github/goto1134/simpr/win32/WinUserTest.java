package com.github.goto1134.simpr.win32;

import com.github.goto1134.simpr.win32.WinUser;
import org.junit.Ignore;
import org.junit.Test;

import java.util.EnumSet;

import static com.github.goto1134.simpr.win32.MessageBoxFlags.ICON_ASTERISK;

/**
 * Created by Andrew
 * on 17.09.2017.
 */
public class WinUserTest {
    @Ignore
    @Test
    public void messageBox()
            throws Exception {
        WinUser.getInstance()
               .showMessage(null, "Hellow, world", "Caption", EnumSet.of(ICON_ASTERISK));
    }

}