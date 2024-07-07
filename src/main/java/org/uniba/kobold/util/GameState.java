package org.uniba.kobold.util;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.room.RoomsMap;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameState {
    private Inventory inventory;
    private String timer;
    private RoomsMap roomsMap;
    private String date = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date());

    public GameState(Inventory inventory, String timerState, RoomsMap roomsMap) {
        this.inventory = inventory;
        this.timer = timerState;
        this.roomsMap = roomsMap;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getDate() {
        return date;
    }

    public String getTimer() {
        return timer;
    }

    public RoomsMap getRoomsMap() {
        return roomsMap;
    }
}