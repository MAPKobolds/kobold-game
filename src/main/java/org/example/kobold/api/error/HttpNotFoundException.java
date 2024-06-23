package org.example.kobold.api.error;

public class HttpNotFoundException extends Exception {
    public HttpNotFoundException(String message) {
        super(message);
    }
}
