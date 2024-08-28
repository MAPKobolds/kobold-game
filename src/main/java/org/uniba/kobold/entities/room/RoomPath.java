package org.uniba.kobold.entities.room;

import org.javatuples.Pair;
import org.uniba.kobold.errors.RoomNotFoundError;
import java.util.List;
import java.util.Objects;

/**
 * The type Room path.
 */
public class RoomPath {
    private List<Pair<Room, Boolean>> paths;

    /**
     * Instantiates a new Room path.
     *
     * @param paths the paths
     */
    public RoomPath(List<Pair<Room, Boolean>> paths) {
        this.paths = paths;
    }

    /**
     * Is accessible boolean.
     *
     * @param roomName the room name
     * @return the boolean
     */
    public boolean isAccessible(String roomName) {
        return paths.stream().anyMatch(path -> path.getValue1() && path.getValue0().getName().equals(roomName));
    }

    /**
     * Sets lock room path.
     *
     * @param roomName the room name
     * @param isLocked the is locked
     */
    public void setLockRoomPath(String roomName, Boolean isLocked) {
        if (!getRoomNames().contains(roomName)) {
            throw new RoomNotFoundError();
        }

        paths = paths.stream().map(pair -> Objects.equals(pair.getValue0().getName(), roomName) ?
            new Pair<>(pair.getValue0(), isLocked) : pair).toList();
    }

    private List<String> getRoomNames() {
        return paths.stream().map(objects -> objects.getValue0().getName()).toList();
    }
}
