package org.example.kobold.api.error;

import java.net.HttpURLConnection;

public class HttpRequestHandler {
    // for now, we will just print the status code to the console
    // next we will implement a way to handle the status code with exceptions
    /**
     * Handle the status code of an HTTP request
     * @param statusCode the status code of the HTTP request
     */
    public static void handle(int statusCode) throws  HttpInternalServerErrorException, HttpNotFoundException, HttpBadRequestException, HttpForbiddenException, HttpUnavailableException {
        switch (statusCode) {
            case HttpURLConnection.HTTP_OK:
                break;
            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                throw new HttpInternalServerErrorException("HTTP_INTERNAL_ERROR");
            case HttpURLConnection.HTTP_NOT_FOUND:
                throw new HttpNotFoundException("HTTP_NOT_FOUND");
            case HttpURLConnection.HTTP_BAD_REQUEST:
                throw new HttpBadRequestException("HTTP_BAD_REQUEST");
            case HttpURLConnection.HTTP_FORBIDDEN:
                throw new HttpForbiddenException("HTTP_FORBIDDEN");
            case HttpURLConnection.HTTP_UNAVAILABLE:
                throw new HttpUnavailableException("HTTP_UNAVAILABLE");
            default:
                System.out.println("UNKNOWN_STATUS");
                break;
        }
    }
}
