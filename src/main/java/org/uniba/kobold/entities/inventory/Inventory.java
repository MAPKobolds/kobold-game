package org.uniba.kobold.entities.inventory;

import java.util.HashSet;
import java.util.Set;

public class Inventory {
    private static final Set<Item> items = new HashSet<>();

    public static void addPiece(Item item) {
        if(!items.contains(item)) {
            items.add(item);
        }
    }

    public void removePiece(Item item) {
        if(!items.contains(item)) {
            throw new Error("This item is not in the inventory");
        }

        items.remove(item);
    }

    public static Set<Item> getItems() {
        return items;
    }
}
