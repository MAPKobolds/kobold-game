package org.uniba.kobold.util;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;
import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.game.Game;
import org.uniba.kobold.guiRef.GuiHubRef;
import org.uniba.kobold.guiRef.PagesEnum;


/**
 * Deserializer class to deserialize the JSON file
 */
public class Deserializer {

    /**
     * loadInstancesFromJSON method to load the instances from JSON
     */
    public static void loadInstancesFromJSON() throws IOException {
        for (String saveName : scanSaves()) {
            new SaveInstance(saveName);
        }
    }

    /**
     * scanSaves method to scan the saves
     * @return the list of saves
     * @throws IOException if there is an error during the JSON reading
     */
    public static ArrayList<String> scanSaves() throws IOException {
        Path dirPath = Paths.get("src/main/resources/saves");
        ArrayList<String> saves = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath, "*.json")) {
            for (Path filePath : stream) {
                String fileName = filePath.getFileName().toString().replace(".json", "");
                String saveName = fileName.split(" -")[0];
                saves.add(saveName);
            }
        } catch (IOException e) {
            throw new IOException("Error during JSON reading", e);
        }
        return saves;
    }

    /**
     * itemsFromJSON method to take items from JSON and fill the Inventory
     * @param filePath the path of the file
     */
    public static void loadFromJson(String filePath) {
        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            Gson gson = new Gson();

            //Deserialize the JSON file
            GameState gameState = gson.fromJson(json, GameState.class);

            //Extract items and timer state and sets them
            new Game();
            GuiHubRef.changeTo(PagesEnum.LOADING);
            Set<Item> items = gameState.items;
            String timerState = gameState.timerState;
            Inventory.setItems(items);

            ManageTimer.getInstance().loadTimer(timerState);
        } catch (IOException e) {
            throw new RuntimeException("Error during JSON reading", e);
        }
    }
}