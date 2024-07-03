package org.uniba.kobold.entities.inventory.availableItems;

import org.uniba.kobold.entities.inventory.Item;

import java.util.Set;

public class Bill extends Item {
    private int value;

    public Bill(int value) {
        super("banconota", Set.of("banconota", "soldi", "denaro", "biglietto", "banconota da " + value + " euro", "/img/banconota.png"), "Una banconota da " + value + " euro.", "/img/BR.png");
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
