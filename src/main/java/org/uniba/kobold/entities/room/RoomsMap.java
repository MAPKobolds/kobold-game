package org.uniba.kobold.entities.room;
import org.javatuples.Pair;
import java.util.List;
import java.util.Map;

public class RoomsMap {
    Map<String, List<Pair<Boolean, Room>> > rooms;
    String currentRoom;

    public RoomsMap(Map<String, List<Pair<Boolean, Room>>> rooms, String initialPositions) {
        if(!rooms.containsKey(initialPositions)) {
            throw new Error("Invalid initial position");
        }

        this.rooms = rooms;
        currentRoom = initialPositions;
    }

    public Room moveToRoom(String roomName){
        Room destination = null;

        for (Pair<Boolean, Room> room : rooms.get(roomName)) {
            if (room.getValue0()) {
                destination = room.getValue1();
                currentRoom = roomName;
            }
        }

        return destination;
    }

    public void nowIsAccessible(String roomName) {
        rooms.forEach((key, value) -> {
            if (key.equals(roomName)) {
                for (Pair<Boolean, Room> room : value) {
                    room = room.setAt0(true);
                }
            }
        });
    }

}
