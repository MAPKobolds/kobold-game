package org.uniba.kobold.util;

import org.uniba.kobold.guiRef.GuiLoadRef;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameSaveInstance {
    private String playerName;
    private int saveCount;
    private String saveDate;

    public GameSaveInstance(String playerName, int saveCount) {
        if (saveCount < 0) {
            throw new IllegalArgumentException("Save count cannot be negative");
        }

        this.playerName = playerName;
        this.saveCount = saveCount;
        setSaveDate(new Date());
    }

    public int getSaveCount() {
        return saveCount;
    }

    public void setSaveCount(int saveCount) {
        this.saveCount = saveCount;
    }

    public void setSaveDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        saveDate = formatter.format(date);
    }

    public String getSaveDate() {
        return this.saveDate;
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
