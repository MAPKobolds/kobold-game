package org.uniba.kobold.entities.room;

import org.javatuples.Pair;
import org.uniba.kobold.errors.RoomNotAccessibleError;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Rooms map.
 */
public class RoomsMap {
    private Map<String, Pair<Room, RoomPath>> rooms = new HashMap<>();
    private String currentRoom;

    /**
     * Instantiates a new Rooms map.
     *
     * @param roomsList the rooms list
     */
    public RoomsMap(List<Pair<Room, RoomPath>> roomsList) {
        roomsList.forEach(r -> rooms.put(r.getValue0().getName(), r));

        this.currentRoom = roomsList.get(0).getValue0().getName();
    }

    /**
     * Gets current room.
     *
     * @return the current room
     */
    public Room getCurrentRoom() {
        return rooms.get(currentRoom).getValue0();
    }

    /**
     * Move to.
     *
     * @param roomName the room name
     * @throws RoomNotAccessibleError the room not accessible error
     */
    public void moveTo(String roomName) throws RoomNotAccessibleError {
        if (!rooms.get(currentRoom).getValue1().isAccessible(roomName)) {
            throw new RoomNotAccessibleError();
        }

        this.currentRoom = roomName;
    }

    /**
     * Unlock path.
     *
     * @param roomName the room name
     */
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
