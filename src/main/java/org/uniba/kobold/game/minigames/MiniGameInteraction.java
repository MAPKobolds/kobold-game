package org.uniba.kobold.game.minigames;

/**
 * The type Mini game interaction.
 */
public class MiniGameInteraction {
    private String info;
    private Object result;
    private MiniGameInteractionType type;

    /**
     * Instantiates a new Mini game interaction.
     *
     * @param info   the info
     * @param result the result
     * @param type   the type
     */
    public MiniGameInteraction(String info, Object result, MiniGameInteractionType type) {
        this.info = info;
        this.result = result;
        this.type = type;
    }

    /**
     * Gets info.
     *
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Gets result.
     *
     * @return the result
     */
    public Object getResult() {
        return result;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public MiniGameInteractionType getType() {
        return type;
    }

    /**
     * Sets info.
     *
     * @param info the info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Add info.
     *
     * @param info the info
     */
    public void addInfo(String info) {
        this.info += info;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(MiniGameInteractionType type) {
        this.type = type;
    }

    /**
     * Sets result.
     *
     * @param obj the obj
     */
    public void setResult(Object obj) {
        this.result = obj;
    }

}
