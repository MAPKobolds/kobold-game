package org.uniba.kobold.entities.inventory;

import javax.swing.*;
import java.util.Objects;
import java.util.Set;

public class Item {
    private String name;
    private Set<String> alias;
    private String description;
    private String image;

    public Item(String name, Set<String> alias, String description, String imagePath) {
        this.name = name;
        this.alias = alias;
        this.description = description;
        this.image = imagePath;
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

    public String getImage() {
        return image;
    }
}
