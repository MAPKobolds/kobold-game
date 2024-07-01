package org.uniba.kobold.entities.room;

public class RoomInteractionResult {
    RoomInteractionResultType resultType;
    String subject = "";

    public RoomInteractionResult(RoomInteractionResultType resultType, String subject) {
        this.resultType = resultType;
        this.subject = subject;
    }

    public RoomInteractionResult(RoomInteractionResultType resultType) {
        this.resultType = resultType;
    }
}
