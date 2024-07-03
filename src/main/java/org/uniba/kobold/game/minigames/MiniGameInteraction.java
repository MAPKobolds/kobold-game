package org.uniba.kobold.game.minigames;

public class MiniGameInteraction {
    String info;
    Boolean hasFinished;
    Object result;
    MiniGameInteractionType type;

    public MiniGameInteraction(String info, Boolean hasFinished, Object result, MiniGameInteractionType type) {
        this.info = info;
        this.hasFinished = hasFinished;
        this.result = result;
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public Boolean getHasFinished() {
        return hasFinished;
    }

    public Object getResult() {
        return result;
    }

    public MiniGameInteractionType getType() {
        return type;
    }
}
