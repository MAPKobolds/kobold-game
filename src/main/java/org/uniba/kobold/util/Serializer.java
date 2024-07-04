package org.uniba.kobold.util;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import com.google.gson.GsonBuilder;
import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.Item;

import javax.swing.*;


/**
 * Serializer class to serialize the JSON file
 */
public class Serializer {

    /**
     * Set of items possessed by the player
     */
    private static final Set<Item> possessedItems = Inventory.getItems();

    /**
     * Method to save the game to a json file
     * @param save the instance of the game
     * @param fileName the path of the file
     */
    public static void saveToJson(SaveInstance save, String fileName) {
        Gson gson = new Gson();

        String timerState = ManageTimer.getTime();
        GameState gameState = new GameState(possessedItems, timerState);
        String json = gson.toJson(gameState);
        String filePath = "src/main/resources/saves/" + fileName + ".json";

        try {
            Files.write(Paths.get(filePath), json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
