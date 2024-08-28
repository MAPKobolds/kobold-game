package org.uniba.kobold.entities.inventory;

import com.google.gson.*;
import org.uniba.kobold.entities.inventory.availableItems.*;

import java.lang.reflect.Type;

/**
 * The type Item deserializer.
 */
public class ItemDeserializer implements JsonDeserializer<Item> {

    @Override
    public Item deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        String itemName = jsonObject.get("name").getAsString();
        Class itemClass = Item.class;

        switch (itemName) {
            case "birre" -> itemClass = Beers.class;
            case "carrozzeria" -> itemClass = CarBody.class;
            case "mantello" -> itemClass = Cloak.class;
            case "motore" -> itemClass = Engine.class;
            case "maglio" -> itemClass = FireMaul.class;
            case "GinMoncello" -> itemClass = GinMoncello.class;
            case "volante" -> itemClass = SteeringWheel.class;
        }

        return jsonDeserializationContext.deserialize(jsonElement, itemClass);
    }
}
