package org.uniba.kobold.api.blackjack;

import java.util.Map;

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
     * @param code the card code
     * @param image the card image
     * @param value the card value
     * @param suit the card suit
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
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public void setValue(String cardValue) {
        value = cardValue;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public Map<String, String> getImages() {
        return images;
    }

    public void setImages(Map<String, String> images) {
        this.images = images;
    }
        
    public String getPngImage() {
        return images.get("png");
    }

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
