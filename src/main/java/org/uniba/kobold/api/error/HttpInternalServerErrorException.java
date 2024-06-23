package org.uniba.kobold.api.error;

public class HttpInternalServerErrorException extends Exception {
    public HttpInternalServerErrorException(String message) {
        super(message);
    }
}
