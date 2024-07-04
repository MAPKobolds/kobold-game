package org.uniba.kobold.util;

import org.uniba.kobold.entities.inventory.Item;

import java.util.Set;

public class GameState {
    Set<Item> items;
    String timerState;

    public GameState(Set<Item> items, String timerState) {
        this.items = items;
        this.timerState = timerState;
    }
}