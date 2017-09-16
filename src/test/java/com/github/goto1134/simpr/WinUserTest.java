package com.github.goto1134.simpr;

import org.junit.Ignore;
import org.junit.Test;

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
               .showMessage(null, "Hellow, world", "Caption", 0x00000000L);
    }

}