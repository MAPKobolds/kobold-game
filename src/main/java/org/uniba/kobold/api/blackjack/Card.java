package org.uniba.kobold.api.blackjack;

import java.util.Map;

/**
 * The type Card.
 */
public class Card {

    /**
     * Attributes of the Card class
     */
    private String code;
    private String value;
    private String suit;
    private Map<String, String> images;

    /**
     * Constructor
     *
     * @param code   the card code
     * @param image  the card image
     * @param value  the card value
     * @param suit   the card suit
     * @param images the card images
     */
    public Card(String code, String image, String value, String suit, Map<String, String> images) {
        this.code = code;
        this.value = value;
        this.suit = suit;
        this.images = images;
    }

    /**
     * Getters and setters for the Card class
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        int ret;
        if (value.equals("JACK") || value.equals("QUEEN") || value.equals("KING")) {
            ret = 10;
        } else if (value.equals("ACE")) {
            ret = 1;
        } else {
            ret = Integer.parseInt(value);
        }
        return ret;
    }

    /**
     * Sets value.
     *
     * @param cardValue the card value
     */
    public void setValue(String cardValue) {
        value = cardValue;
    }

    /**
     * Gets suit.
     *
     * @return the suit
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Sets suit.
     *
     * @param suit the suit
     */
    public void setSuit(String suit) {
        this.suit = suit;
    }

    /**
     * Gets images.
     *
     * @return the images
     */
    public Map<String, String> getImages() {
        return images;
    }

    /**
     * Sets images.
     *
     * @param images the images
     */
    public void setImages(Map<String, String> images) {
        this.images = images;
    }

    /**
     * Gets png image.
     *
     * @return the png image
     */
    public String getPngImage() {
        return images.get("png");
    }

    /**
     * Gets svg image.
     *
     * @return the svg image
     */
    public String getSvgImage() {
        return images.get("svg");
    }

    /**
     * Override the toString method
     * @return a string representation of the Card object
     */
    @Override
    public String toString() {
        return "Card{" +
                "code='" + code + '\'' +
                ", value='" + value + '\'' +
                ", suit='" + suit + '\'' +
                ", images=" + images +
                '}';
    }
}
