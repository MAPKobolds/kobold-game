package org.uniba.kobold.entities.room;

import org.javatuples.Pair;
import org.uniba.kobold.errors.RoomNotAccessible;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomsMap {
    private Map<String, Pair<Room, RoomPath>> rooms = new HashMap<>();
    private String currentRoom;

    public RoomsMap(List<Pair<Room, RoomPath>> roomsList) {
        roomsList.forEach(r -> rooms.put(r.getValue0().getName(), r));

        this.currentRoom = roomsList.get(0).getValue0().getName();
    }

    public Room getCurrentRoom() {
        return rooms.get(currentRoom).getValue0();
    }

    public void moveTo(String roomName) {
        if (!rooms.get(currentRoom).getValue1().isAccessible(roomName)) {
            throw new RoomNotAccessible();
        }

        this.currentRoom = roomName;
    }

    public void unlockPath(String roomName) {
        try {
            Room room = rooms.get(currentRoom).getValue0();
            RoomPath path = rooms.get(currentRoom).getValue1();

            path.setLockRoomPath(roomName, true);

            rooms.replace(currentRoom, Pair.with(room, path));
        }
        catch (Error e) {
            throw e;
        }
    }

}
