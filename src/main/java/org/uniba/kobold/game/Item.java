package org.uniba.kobold.game;

import java.util.ArrayList;


public class Item {

    /**
     * Attributes of the class Item
     */
    private String name;
    private String description;

    /**
     * Constructor of the Item class
     */
    public Item() {
        setDescription("");
        setName("");
    }

    /**
     * Constructor of the Item class
     * @param name name of the item
     * @param description description of the item
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Setter for the name of the item
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Setter for the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the name
     * @return name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the description
     * @return description of the item
     */
    public String getDescription() {
        return description;
    }
}
