package org.uniba.kobold.api.error;

/**
 * The type Http internal server error exception.
 */
public class HttpInternalServerErrorException extends Exception {
    /**
     * Instantiates a new Http internal server error exception.
     *
     * @param message the message
     */
    public HttpInternalServerErrorException(String message) {
        super(message);
    }
}
