package org.example.kobold.api.error;

public class HttpForbiddenException extends Exception {
    public HttpForbiddenException(String message) {
        super(message);
    }
}
