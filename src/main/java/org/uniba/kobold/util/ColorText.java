package org.uniba.kobold.util;

public class ColorText {

    public static final String RESET = "</span><span style='color: white;'>";
    public static final String RED = "<span style=\"color: rgb(255, 0, 0);\">";
    public static final String GREEN = "<span style=\"color: rgb(0, 255, 0);\">";
    public static final String BLUE = "<span style=\"color: rgb(0, 0, 255);\">";
    public static final String PURPLE = "<span style=\"color: rgb(128, 0, 128);\">";
    public static final String ORANGE = "<span style=\"color: rgb(255, 165, 0);\">";
    public static final String YELLOW = "<span style=\"color: rgb(255, 255, 0);\">";
    public static final String BLACK = "<span style=\"color: rgb(0, 0, 0);>";

    public static String setTextGreen(String text) {
        return GREEN + text + RESET;
    }

    public static String setTextRed(String text) {
        return RED + text + RESET;
    }

    public static String setTextBlue(String text) {
        return BLUE + text + RESET;
    }

    public static String setTextPurple(String text) {
        return PURPLE + text + RESET;
    }

    public static  String setTextOrange(String text) {
        return ORANGE + text + RESET;
    }

    public static String setTextBlack(String text) {
        return BLACK + text + RESET;
    }

    public static String setTextYellow(String text) {
        return YELLOW + text + RESET;
    }
}
