package org.uniba.kobold.game;

public class GameCommandResult {
    private GameCommandResultType gameCommandResultType;
    private String description;
    private String path;

    public GameCommandResult(GameCommandResultType gameCommandResultType, String description) {
        this.gameCommandResultType = gameCommandResultType;
        this.description = description;
    }

    public GameCommandResult(GameCommandResultType gameCommandResultType, String description, String path) {
        this.gameCommandResultType = gameCommandResultType;
        this.description = description;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
