package org.uniba.kobold.entities.inventory;

import java.util.Set;

public class Inventory {
    private static Inventory instance = getInstance();
    private Set<Class> items;

    public static Inventory getInstance() {
        return instance == null ? new Inventory() : instance;
    }

    public void addPiece(Class item) {
        if(!Item.class.isAssignableFrom(item)) {
            throw new Error("This is not an item");
        }

        if(!items.contains(item)) {
            items.add(item);
        }
    }

    public void removePiece(Class item) {
        if(!items.contains(item)) {
            throw new Error("This item is not in the inventory");
        }

        items.remove(item);
    }
}
