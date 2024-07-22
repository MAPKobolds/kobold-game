package org.uniba.kobold.util;

/**
 * The type Color text.
 */
public class ColorText {

    /**
     * The constant RESET.
     */
    public static final String RESET = "</span><span style='color: white;'>";
    /**
     * The constant RED.
     */
    public static final String RED = "<span style=\"color: rgb(255, 0, 0);\">";
    /**
     * The constant GREEN.
     */
    public static final String GREEN = "<span style=\"color: rgb(0, 255, 0);\">";
    /**
     * The constant BLUE.
     */
    public static final String BLUE = "<span style=\"color: rgb(0, 0, 255);\">";
    /**
     * The constant PURPLE.
     */
    public static final String PURPLE = "<span style=\"color: rgb(128, 0, 128);\">";
    /**
     * The constant ORANGE.
     */
    public static final String ORANGE = "<span style=\"color: rgb(255, 165, 0);\">";
    /**
     * The constant YELLOW.
     */
    public static final String YELLOW = "<span style=\"color: rgb(255, 255, 0);\">";
    /**
     * The constant BLACK.
     */
    public static final String BLACK = "<span style=\"color: rgb(0, 0, 0);>";

    /**
     * Sets text green.
     *
     * @param text the text
     * @return the text green
     */
    public static String setTextGreen(String text) {
        return GREEN + text + RESET;
    }

    /**
     * Sets text red.
     *
     * @param text the text
     * @return the text red
     */
    public static String setTextRed(String text) {
        return RED + text + RESET;
    }

    /**
     * Sets text blue.
     *
     * @param text the text
     * @return the text blue
     */
    public static String setTextBlue(String text) {
        return BLUE + text + RESET;
    }

    /**
     * Sets text purple.
     *
     * @param text the text
     * @return the text purple
     */
    public static String setTextPurple(String text) {
        return PURPLE + text + RESET;
    }

    /**
     * Sets text orange.
     *
     * @param text the text
     * @return the text orange
     */
    public static  String setTextOrange(String text) {
        return ORANGE + text + RESET;
    }

    /**
     * Sets text black.
     *
     * @param text the text
     * @return the text black
     */
    public static String setTextBlack(String text) {
        return BLACK + text + RESET;
    }

    /**
     * Sets text yellow.
     *
     * @param text the text
     * @return the text yellow
     */
    public static String setTextYellow(String text) {
        return YELLOW + text + RESET;
    }
}
