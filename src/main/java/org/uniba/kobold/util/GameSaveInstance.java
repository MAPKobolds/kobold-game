package org.uniba.kobold.util;

import org.uniba.kobold.guiRef.GuiLoadRef;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameSaveInstance {
    private String playerName;
    private int saveCount;
    private String saveDate;

    public GameSaveInstance(String playerName, int saveCount, String saveDate) {
        if (saveCount < 0) {
            throw new IllegalArgumentException("Save count cannot be negative");
        }

        this.playerName = playerName;
        this.saveCount = saveCount;
        this.saveDate = saveDate;
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

    @Override
    public String toString() {
        return playerName + "-" + saveCount;
    }
}
