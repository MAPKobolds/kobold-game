package org.uniba.kobold.api.error;

public class HttpBadRequestException extends Exception {
    public HttpBadRequestException(String message) {
        super(message);
    }
}