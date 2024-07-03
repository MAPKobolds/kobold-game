package org.uniba.kobold.entities.inventory;

import org.uniba.kobold.entities.inventory.availableItems.Bill;

import java.util.HashSet;
import java.util.Set;

public class Inventory {
    private static Inventory instance;
    private static final Set<Item> items = new HashSet<>();

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public static void addPiece(Item item) {
        if(!items.contains(item)) {
            items.add(item);
        }
    }

    public static void removePiece(Item item) {
        if(!items.contains(item)) {
            throw new Error("This item is not in the inventory");
        }

        items.remove(item);
    }

    public static boolean contains(String name) {
        return items.stream().anyMatch(i -> ((Item) i).getName().equals(name));
    }

    public static Set<Item> getItems() {
        return items;
    }

    public static boolean findCurrency(int value) {
        boolean found = false;
        for (Item item : items) {
            if (item instanceof Bill){
                if (((Bill) item).getValue() == value) {
                    found = true;
                }
            }
        }
        return found;
    }
}
