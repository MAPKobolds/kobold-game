package org.uniba.kobold.entities.inventory;

import java.util.Set;

/**
 * The type Item.
 */
public class Item {
    private String name;
    private Set<String> alias;
    private String description;
    private String image;

    /**
     * Instantiates a new Item.
     *
     * @param name        the name
     * @param alias       the alias
     * @param description the description
     * @param imagePath   the image path
     */
    public Item(String name, Set<String> alias, String description, String imagePath) {
        this.name = name;
        this.alias = alias;
        this.description = description;
        this.image = imagePath;
    }

    /**
     * Gets alias.
     *
     * @return the alias
     */
    public Set<String> getAlias() {
        return alias;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }
}
