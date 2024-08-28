package org.uniba.kobold.util;

import java.nio.file.Path;

/**
 * The type Game save instance.
 */
public class GameSaveInstance {
    private Path filePath;
    private String playerName;
    private int saveCount;
    private String saveDate;

    /**
     * Instantiates a new Game save instance.
     *
     * @param playerName the player name
     * @param saveCount  the save count
     * @param saveDate   the save date
     * @param filePath   the file path
     */
    public GameSaveInstance(String playerName, int saveCount, String saveDate, Path filePath) {
        if (saveCount < 0) {
            throw new IllegalArgumentException("Save count cannot be negative");
        }

        this.playerName = playerName;
        this.saveCount = saveCount;
        this.saveDate = saveDate;
        this.filePath = filePath;
    }

    /**
     * Gets save count.
     *
     * @return the save count
     */
    public int getSaveCount() {
        return saveCount;
    }

    /**
     * Gets save date.
     *
     * @return the save date
     */
    public String getSaveDate() {
        return this.saveDate;
    }

    /**
     * Gets player name.
     *
     * @return the player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gets file path.
     *
     * @return the file path
     */
    public Path getFilePath() {
        return filePath;
    }

    @Override
    public String toString() {
        return playerName + "-" + saveCount;
    }
}
