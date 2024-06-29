package org.uniba.kobold.entities.inventory;

import javax.swing.*;
import java.util.Objects;

public class Item {
    private String name;
    private String description;
    private ImageIcon image;

    public Item(String name, String description, String imagePath) {
        this.name = name;
        this.description = description;
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public ImageIcon getImage() {
        return image;
    }
}
