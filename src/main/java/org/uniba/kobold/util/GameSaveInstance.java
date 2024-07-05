package org.uniba.kobold.util;

public class GameSaveInstance {
    private String playerName;
    private int saveCount;

    public GameSaveInstance(String playerName, int saveCount) {
        if (saveCount < 0) {
            throw new IllegalArgumentException("Save count cannot be negative");
        }

        this.playerName = playerName;
        this.saveCount = saveCount;
    }

    public int getSaveCount() {
        return saveCount;
    }

    public void setSaveCount(int saveCount) {
        this.saveCount = saveCount;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return playerName + "-" + saveCount;
    }
}
