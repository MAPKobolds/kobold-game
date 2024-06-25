package org.uniba.kobold.entities.item;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class Item {
    private String name;
    private String description;
    private ImageIcon image;

    public Item(String name, String description, String imagePath) throws IOException {
        this.name = name;
        this.description = description;
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/BR.png")));
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
