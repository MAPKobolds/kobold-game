package org.uniba.kobold.game;

public class GameCommandResult {
    private GameCommandResultType gameCommandResultType;
    private String description;

    public GameCommandResult(GameCommandResultType gameCommandResultType, String description) {
        this.gameCommandResultType = gameCommandResultType;
        this.description = description;
    }

    public GameCommandResultType getGameCommandResultType() {
        return gameCommandResultType;
    }

    public String getDescription() {
        return description;
    }

    public void setGameCommandResultType(GameCommandResultType gameCommandResultType) {
        this.gameCommandResultType = gameCommandResultType;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
