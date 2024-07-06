package org.uniba.kobold.util;

import com.google.gson.Gson;
import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.game.Game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class GameConverter {

    /**
     * itemsFromJSON method to take items from JSON and fill the Inventory
     * @param filePath the path of the file
     */
    public static GameState deserialize(Path filePath) {
        try {
            String json = new String(Files.readAllBytes(filePath));
            Gson gson = new Gson();

            //Deserialize the JSON file
            return gson.fromJson(json, GameState.class);
        } catch (IOException e) {
            throw new RuntimeException("Error during JSON reading", e);
        }
    }

    public static void serialize(Game game, String time, Set<Item> items) {
        Gson gson = new Gson();

        GameState gameState = new GameState(items, time, game.getCurrentRoom().getName());
        String json = gson.toJson(gameState);
        String filePath = "src/main/resources/saves/" + game.getPlayerName() + "-" + (GameSave.getNumberOfUserSave(game.getPlayerName()) + 1) + ".json";

        try {
            Files.write(Paths.get(filePath), json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
