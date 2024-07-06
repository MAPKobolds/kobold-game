package org.uniba.kobold.util;

import java.nio.file.Path;

public class GameSaveInstance {
    private Path filePath;
    private String playerName;
    private int saveCount;
    private String saveDate;

    public GameSaveInstance(String playerName, int saveCount, String saveDate, Path filePath) {
        if (saveCount < 0) {
            throw new IllegalArgumentException("Save count cannot be negative");
        }

        this.playerName = playerName;
        this.saveCount = saveCount;
        this.saveDate = saveDate;
        this.filePath = filePath;
    }

    public int getSaveCount() {
        return saveCount;
    }
    public String getSaveDate() {
        return this.saveDate;
    }
    public String getPlayerName() {
        return playerName;
    }
    public Path getFilePath() {
        return filePath;
    }

    @Override
    public String toString() {
        return playerName + "-" + saveCount;
    }
}
