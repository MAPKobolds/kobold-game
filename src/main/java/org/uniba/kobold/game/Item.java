package org.uniba.kobold.game;

import org.uniba.kobold.gui.GuiObjectButton;

import javax.swing.*;

public class Item {

    /**
     * Attributes of the class Item
     */
    private String name;
    private String description;
    private final String objectImagePath;
    private final JButton itemButton;

    /**
     * Constructor of the Item class
     * @param name name of the item
     * @param description description of the item
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.objectImagePath = "/img/BR.png";
        //this.objectImagePath = "/img/" + name + ".png";
        this.itemButton = new GuiObjectButton(
                name,
                objectImagePath
        ).getButton();
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

    /**
     * Getter for the item button
     * @return item button
     */
    public JButton getItemButton() {
        return itemButton;
    }
}
