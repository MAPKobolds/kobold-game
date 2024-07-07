package org.uniba.kobold.entities.inventory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Inventory {
    private final Set<Item> items = new HashSet<>();
    private int money = 0;

    public Inventory(List<Item> items, int money) {
        this.items.addAll(items);
        this.money = money;
    }

    public void addMoney(int value) {
        this.money += value;
    }

    public void removeMoney(int value) {
        this.money -= value;
    }

    public int getMoney() {
        return this.money;
    }
    
    public void addPiece(Item item) {
        this.items.add(item);
    }

    public void removePiece(String name) {
        this.items.removeIf(i -> ((Item) i).getName().equals(name));
    }

    public boolean contains(String name) {
        return this.items.stream().anyMatch(i -> ((Item) i).getName().equals(name));
    }

    public void setItems(Set<Item> deserializedItems) {
        this.items.clear();
        deserializedItems.forEach(this::addPiece);
    }

    public Set<Item> getItems() {
        return this.items;
    }

    public List<Item> getInventory() {
        return List.copyOf(this.items);
    }
}
