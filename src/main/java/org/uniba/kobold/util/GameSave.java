package org.uniba.kobold.util;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameSave {
    final static String FOLDER_PATH = "src/main/resources/saves/";
    public static GameSave instance;

    public static List<GameSaveInstance> getSavingName() throws Error {
        Path dirPath = Paths.get(FOLDER_PATH);
        List<GameSaveInstance> saves =  new ArrayList(Arrays.asList());

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath, "*.json")) {
            for (Path filePath : stream) {
                String fileName = filePath.getFileName().toString().replace(".json", "");
                String[] stringSave = (fileName.split(".json")[0]).split("-");

                if (stringSave.length != 2) {
                    throw new Error("Invalid save file");
                }

                saves.add(new GameSaveInstance(stringSave[0], Integer.parseInt(stringSave[1]), GameConverter.deserialize(filePath).getDate()));
            }
        } catch (IOException e) {
            throw new Error("Error during JSON reading", e);
        }
        return saves;
    }

    public static void deleteSave(GameSaveInstance save) throws IOException {
        try {
            String filePath = FOLDER_PATH + save.toString() + ".json";
            Path path = Paths.get(filePath);
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new IOException("This file does not exists", e);
        }
    }

    public static int getNumberOfUserSave(String name) throws Error {
        try {
            return (int) getSavingName().stream().filter(n -> n.getPlayerName().contains(name)).count();
        } catch (Error e) {
            throw new Error("Error during reading files", e);
        }
    }

    public static void loadSave(GameSaveInstance save) {
        Deserializer.loadFromJson("src/main/resources/saves/"
                + save.getPlayerName() + "-" + save.getSaveCount() + ".json");
    }
}
