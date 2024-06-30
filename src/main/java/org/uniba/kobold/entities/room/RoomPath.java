package org.uniba.kobold.entities.room;

import org.javatuples.Pair;
import java.util.List;
import java.util.Objects;

public class RoomPath {
    private List<Pair<Room, Boolean>> paths;

    public RoomPath(List<Pair<Room, Boolean>> paths) {
        this.paths = paths;
    }

    public boolean isAccessible(String roomName) {
        return paths.stream().anyMatch(path -> path.getValue1() && path.getValue0().getName() == roomName);
    }

    public void setLockRoomPath(String roomName, Boolean isLocked) {
        if (!getRoomNames().contains(roomName)) {
            throw new Error("Invalid room name");
        }

        paths = paths.stream().map(pair -> Objects.equals(pair.getValue0().getName(), roomName) ?
            new Pair<>(pair.getValue0(), isLocked) : pair).toList();
    }

    private List<String> getRoomNames() {
        return paths.stream().map(objects -> objects.getValue0().getName()).toList();
    }
}
