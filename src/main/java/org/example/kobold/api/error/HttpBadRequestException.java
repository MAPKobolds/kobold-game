package org.example.kobold.api.error;

public class HttpBadRequestException extends Exception {
    public HttpBadRequestException(String message) {
        super(message);
    }
}