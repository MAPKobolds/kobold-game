package org.uniba.kobold.entities.room;

import com.google.gson.*;
import org.uniba.kobold.entities.room.avaliableRooms.*;

import java.lang.reflect.Type;

/**
 * The type Room deserializer.
 */
public class RoomDeserializer implements JsonDeserializer<Room> {

    @Override
    public Room deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        String roomName = jsonObject.get("name").getAsString();
        Class roomClass = Room.class;

        switch (roomName) {
            case "introduzione" -> roomClass = IntroductionRoom.class;
            case "corridoio" -> roomClass = HallwayRoom.class;
            case "circuito" -> roomClass = CircuitRoom.class;
            case "fucine" -> roomClass = ForgeRoom.class;
            case "palazzo" -> roomClass = PalaceEntryRoom.class;
            case "generatore" -> roomClass = PowerHouseRoom.class;
            case "taverna" -> roomClass = TavernRoom.class;
            case "spiazzale" -> roomClass = SquareRoom.class;
            case "caverna" -> roomClass = StartingRoom.class;
            case "trono" -> roomClass = ThroneRoom.class;
        }

        return jsonDeserializationContext.deserialize(jsonElement, roomClass);
    }
}
