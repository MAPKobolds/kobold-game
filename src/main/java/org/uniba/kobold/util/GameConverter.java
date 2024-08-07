package org.uniba.kobold.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.uniba.kobold.entities.inventory.*;
import org.uniba.kobold.entities.room.Room;
import org.uniba.kobold.entities.room.RoomDeserializer;
import org.uniba.kobold.game.Game;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The type Game converter.
 */
public class GameConverter {

    /**
     * itemsFromJSON method to take items from JSON and fill the Inventory
     *
     * @param filePath the path of the file
     * @return the game state
     */
    public static GameState deserialize(Path filePath) {
        try {
            String json = new String(Files.readAllBytes(filePath));
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Room.class, new RoomDeserializer())
                    .create();

            //Deserialize the JSON file
            return gson.fromJson(json, GameState.class);
        } catch (IOException e) {
            throw new RuntimeException("Error during JSON reading", e);
        }
    }

    /**
     * Serialize.
     *
     * @param game the game
     * @param time the time
     */
    public static void serialize(Game game, String time) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Room.class, new RoomDeserializer())
                .registerTypeAdapter(Item.class, new ItemDeserializer())
                .create();

        GameState gameState = new GameState(game.getInventory(), time, game.getCurrentRoomMap());
        String json = gson.toJson(gameState);
        String filePath = "src/main/resources/saves/" + game.getPlayerName() + "-" + (GameSave.getNumberOfUserSave(game.getPlayerName()) + 1) + ".json";

        try {
            Files.write(Paths.get(filePath), json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
