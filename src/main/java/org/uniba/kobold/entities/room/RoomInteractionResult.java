package org.uniba.kobold.entities.room;

/**
 * The type Room interaction result.
 */
public class RoomInteractionResult {
    private RoomInteractionResultType resultType;
    private String subject = "";
    private Object argument;

    /**
     * Instantiates a new Room interaction result.
     *
     * @param resultType the result type
     * @param subject    the subject
     */
    public RoomInteractionResult(RoomInteractionResultType resultType, String subject) {
        this.resultType = resultType;
        this.subject = subject;
    }

    /**
     * Instantiates a new Room interaction result.
     *
     * @param resultType the result type
     */
    public RoomInteractionResult(RoomInteractionResultType resultType) {
        this.resultType = resultType;
    }

    /**
     * Gets result type.
     *
     * @return the result type
     */
    public RoomInteractionResultType getResultType() {
        return resultType;
    }

    /**
     * Gets subject.
     *
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets result type.
     *
     * @param resultType the result type
     */
    public void setResultType(RoomInteractionResultType resultType) {
        this.resultType = resultType;
    }

    /**
     * Sets subject.
     *
     * @param subject the subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets argument.
     *
     * @return the argument
     */
    public Object getArgument() {
        return argument;
    }

    /**
     * Sets argument.
     *
     * @param argument the argument
     */
    public void setArgument(Object argument) {
        this.argument = argument;
    }
}
