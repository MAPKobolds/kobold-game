package org.uniba.kobold.util;

import org.uniba.kobold.gui.GuiLoadGame;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * SaveInstance class to save instances of the game

 */
public class SaveInstance {

    /**
     * Attributes of the class SaveInstance
     */
    private static final ArrayList<SaveInstance> instances = new ArrayList<>();
    private static final HashMap<String, Integer> playerSaveCount = new HashMap<>();
    private static final int MAXSAVES = 8;
    private String saveName;
    private String saveDate;

    /**
     * Constructor to add the instance to the list
     */
    public SaveInstance(String savePlayer) {
        if(instances.size() < MAXSAVES) {
            int count = playerSaveCount.getOrDefault(savePlayer, 0) + 1;
            playerSaveCount.put(savePlayer, count);
            this.saveName = savePlayer + " - " + count;

            setSaveDate(new Date());
            instances.add(this);
            SwingUtilities.invokeLater(() -> new GuiLoadGame.SaveInstancePanel(this));
        }
    }

    /**
     * deleteSave to delete save games and their files
     */
    public void deleteSave() throws IOException {
        try {
            String filePath = "src/main/resources/saves/" + this.saveName + ".json";
            Path path = Paths.get(filePath);
            Files.deleteIfExists(path);
            instances.remove(this);

        } catch (IOException e) {
            throw new IOException("Error during JSON reading", e);
        }
    }

    public void loadSave() {
        Deserializer.loadFromJson("src/main/resources/saves/" + this.saveName + ".json");
    }

    /**
     * Getter for the list of instances
     * @return the list of instances
     */
    public static ArrayList<SaveInstance> getInstances() {
        return instances;
    }

    /**
     * Setter for the date of the save
     * @param date the date of the save
     */
    public void setSaveDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        saveDate = formatter.format(date);
    }

    /**
     * Getter for the date of the save
     * @return the date of the save
     */
    public String getSaveDate() {
        return saveDate;
    }

    /**
     * Getter for the name of the save
     * @return the name of the save
     */
    public String getSaveName() {
        return saveName;
    }
}
