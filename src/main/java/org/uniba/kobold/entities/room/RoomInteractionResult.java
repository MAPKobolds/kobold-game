package org.uniba.kobold.entities.room;

public class RoomInteractionResult {
    private RoomInteractionResultType resultType;
    private String subject = "";

    public RoomInteractionResult(RoomInteractionResultType resultType, String subject) {
        this.resultType = resultType;
        this.subject = subject;
    }

    public RoomInteractionResult(RoomInteractionResultType resultType) {
        this.resultType = resultType;
    }

    public RoomInteractionResultType getResultType() {
        return resultType;
    }

    public String getSubject() {
        return subject;
    }

    public void setResultType(RoomInteractionResultType resultType) {
        this.resultType = resultType;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
