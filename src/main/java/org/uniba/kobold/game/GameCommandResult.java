package org.uniba.kobold.game;

/**
 * The type Game command result.
 */
public class GameCommandResult {
    private GameCommandResultType gameCommandResultType;
    private String description;
    private String path;

    /**
     * Instantiates a new Game command result.
     *
     * @param gameCommandResultType the game command result type
     * @param description           the description
     */
    public GameCommandResult(GameCommandResultType gameCommandResultType, String description) {
        this.gameCommandResultType = gameCommandResultType;
        this.description = description;
    }

    /**
     * Instantiates a new Game command result.
     *
     * @param gameCommandResultType the game command result type
     * @param description           the description
     * @param path                  the path
     */
    public GameCommandResult(GameCommandResultType gameCommandResultType, String description, String path) {
        this.gameCommandResultType = gameCommandResultType;
        this.description = description;
        this.path = path;
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets path.
     *
     * @param path the path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets game command result type.
     *
     * @return the game command result type
     */
    public GameCommandResultType getGameCommandResultType() {
        return gameCommandResultType;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets game command result type.
     *
     * @param gameCommandResultType the game command result type
     */
    public void setGameCommandResultType(GameCommandResultType gameCommandResultType) {
        this.gameCommandResultType = gameCommandResultType;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
