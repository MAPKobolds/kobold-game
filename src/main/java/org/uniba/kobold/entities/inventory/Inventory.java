package org.uniba.kobold.entities.inventory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type Inventory.
 */
public class Inventory {
    private final Set<Item> items = new HashSet<>();
    private int money = 0;

    /**
     * Instantiates a new Inventory.
     *
     * @param items the items
     * @param money the money
     */
    public Inventory(List<Item> items, int money) {
        this.items.addAll(items);
        this.money = money;
    }

    /**
     * Add money.
     *
     * @param value the value
     */
    public void addMoney(int value) {
        this.money += value;
    }

    /**
     * Remove money.
     *
     * @param value the value
     */
    public void removeMoney(int value) {
        this.money -= value;
    }

    /**
     * Gets money.
     *
     * @return the money
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * Add piece.
     *
     * @param item the item
     */
    public void addPiece(Item item) {
        this.items.add(item);
    }

    /**
     * Remove piece.
     *
     * @param name the name
     */
    public void removePiece(String name) {
        this.items.removeIf(i -> ((Item) i).getName().equals(name));
    }

    /**
     * Contains boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean contains(String name) {
        return this.items.stream().anyMatch(i -> ((Item) i).getName().equals(name));
    }

    /**
     * Sets items.
     *
     * @param deserializedItems the deserialized items
     */
    public void setItems(Set<Item> deserializedItems) {
        this.items.clear();
        deserializedItems.forEach(this::addPiece);
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public Set<Item> getItems() {
        return this.items;
    }

    /**
     * Gets inventory.
     *
     * @return the inventory
     */
    public List<Item> getInventory() {
        return List.copyOf(this.items);
    }
}
