package org.uniba.kobold.game.minigames;

public class MiniGameInteraction {
    private String info;
    private Object result;
    private MiniGameInteractionType type;

    public MiniGameInteraction(String info, Object result, MiniGameInteractionType type) {
        this.info = info;
        this.result = result;
        this.type = type;
    }

    public String getInfo() {
        return info;
    }


    public Object getResult() {
        return result;
    }

    public MiniGameInteractionType getType() {
        return type;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void addInfo(String info) {
        this.info += info;
    }

    public void setType(MiniGameInteractionType type) {
        this.type = type;
    }
    
    public void setResult(Object obj) {
        this.result = obj;
    }

}
