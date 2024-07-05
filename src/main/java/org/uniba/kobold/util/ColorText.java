package org.uniba.kobold.util;

public class ColorText {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_ORANGE = "\u001B[38;5;208m";

    public static String setTextGreen(String text) {
        return ANSI_GREEN + text + ANSI_RESET;
    }

    public static String setTextRed(String text) {
        return ANSI_RED + text + ANSI_RESET;
    }

    public static String setTextBlue(String text) {
        return ANSI_BLUE + text + ANSI_RESET;
    }

    public static String setTextPurple(String text) {
        return ANSI_PURPLE + text + ANSI_RESET;
    }

    public static  String setTextOrange(String text) {
        return ANSI_ORANGE + text + ANSI_RESET;
    }

}
