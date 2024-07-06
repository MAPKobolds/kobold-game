package org.uniba.kobold.util;

import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.entities.room.Room;

import java.util.Set;

public class GameState {
    Set<Item> items;
    String timerState;
    Room currentRoom;

    public GameState(Set<Item> items, String timerState, Room currentRoom) {
        this.items = items;
        this.timerState = timerState;
        this.currentRoom = currentRoom;
    }
}