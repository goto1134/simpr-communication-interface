package com.github.goto1134.simpr;

/**
 * Created by Andrew
 * on 14.09.2017.
 */
public enum Win32Encoding {
    ANSI,
    UNICODE;
    public static final String ENCODING_IS_UNICODE_KEY = "com.github.goto1134.use.unicode";
    public static Win32Encoding systemEncoding = Boolean.getBoolean(ENCODING_IS_UNICODE_KEY) ? Win32Encoding.UNICODE
                                                                                             : Win32Encoding.ANSI;

    public static boolean isANSI() {return !isUnicode();}

    public static boolean isUnicode() { return systemEncoding == UNICODE;}
}
