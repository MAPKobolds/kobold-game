package org.uniba.kobold.entities.inventory;

import javax.swing.*;
import java.util.Objects;
import java.util.Set;

public class Item {
    private String name;
    private Set<String> alias;
    private String description;
    private ImageIcon image;

    public Item(String name, Set<String> alias, String description, String imagePath) {
        this.name = name;
        this.alias = alias;
        this.description = description;
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
    }

    public Set<String> getAlias() {
        return alias;
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
