package org.uniba.kobold.util;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.room.RoomsMap;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Game state.
 */
public class GameState {
    private Inventory inventory;
    private String timer;
    private RoomsMap roomsMap;
    private String date = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date());

    /**
     * Instantiates a new Game state.
     *
     * @param inventory  the inventory
     * @param timerState the timer state
     * @param roomsMap   the rooms map
     */
    public GameState(Inventory inventory, String timerState, RoomsMap roomsMap) {
        this.inventory = inventory;
        this.timer = timerState;
        this.roomsMap = roomsMap;
    }

    /**
     * Gets inventory.
     *
     * @return the inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets timer.
     *
     * @return the timer
     */
    public String getTimer() {
        return timer;
    }

    /**
     * Gets rooms map.
     *
     * @return the rooms map
     */
    public RoomsMap getRoomsMap() {
        return roomsMap;
    }
}