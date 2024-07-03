package org.uniba.kobold.entities.inventory;

import org.uniba.kobold.entities.inventory.availableItems.Bill;

import java.util.HashSet;
import java.util.Set;

public class Inventory {
    private static Inventory instance;
    private static final Set<Item> items = new HashSet<>();
    private static int money = 0;

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public static void addMoney(int value) {
        money += value;
    }

    public static void removeMoney(int value) {
        money -= value;
    }

    public static int getMoney() {
        return money;
    }
    
    public static void addPiece(Item item) {
        items.add(item);
    }

    public static void removePiece(Item item) {
        for (Item i : items) {
            if (i.equals(item)) {
                items.remove(i);
                break;
            }
        }
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
