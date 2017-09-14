package com.github.goto1134.simpr;

/**
 * Created by Andrew
 * on 14.09.2017.
 */
public enum Win32Encoding {
    ANSI,
    UNICODE;
    public static final String ENCODING_IS_ANSI_FLAG = "com.github.goto1134.isANSI";
    public static Win32Encoding systemEncoding = Boolean.getBoolean(ENCODING_IS_ANSI_FLAG) ? Win32Encoding.ANSI
                                                                                           : Win32Encoding.UNICODE;

    public static boolean isANSI() {return !isUnicode();}

    public static boolean isUnicode() { return systemEncoding == UNICODE;}
}
