package org.uniba.kobold.util;

import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.entities.room.Room;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class GameState {
    Set<Item> items;
    String timerState;
    String currentRoom;
    String date = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date());

    public GameState(Set<Item> items, String timerState, String currentRoom) {
        this.items = items;
        this.timerState = timerState;
        this.currentRoom = currentRoom;
    }

    public String getDate() {
        return date;
    }
}