package org.uniba.kobold.entities.character;

import com.google.gson.*;
import org.uniba.kobold.entities.character.availableCharacters.TwinGuards;
import org.uniba.kobold.entities.room.avaliableRooms.*;
import java.lang.reflect.Type;

public class CharactersDeserializer implements JsonDeserializer<Character> {
    @Override
    public Character deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        String roomName = jsonObject.get("name").getAsString();
        Class characterClass = Character.class;

        switch (roomName) {
            case "guardie" -> characterClass = TwinGuards.class;
        }

        return jsonDeserializationContext.deserialize(jsonElement, characterClass);
    }
}
