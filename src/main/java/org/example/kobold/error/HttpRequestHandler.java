package org.example.kobold.error;

import java.net.HttpURLConnection;

public class HttpRequestHandler {
    // for now, we will just print the status code to the console
    // next we will implement a way to handle the status code with exceptions
    /**
     * Handle the status code of an HTTP request
     * @param statusCode the status code of the HTTP request
     */
    public static void handle(int statusCode) {
        switch (statusCode) {
            case HttpURLConnection.HTTP_OK:
                System.out.println("HTTP_OK");
                break;
            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                System.out.println("HTTP_INTERNAL_ERROR");
                break;
            case HttpURLConnection.HTTP_NOT_FOUND:
                System.out.println("HTTP_NOT_FOUND");
                break;
            case HttpURLConnection.HTTP_BAD_REQUEST:
                System.out.println("HTTP_BAD_REQUEST");
                break;
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                System.out.println("HTTP_UNAUTHORIZED");
                break;
            case HttpURLConnection.HTTP_FORBIDDEN:
                System.out.println("HTTP_FORBIDDEN");
                break;
            case HttpURLConnection.HTTP_NOT_IMPLEMENTED:
                System.out.println("HTTP_NOT_IMPLEMENTED");
                break;
            case HttpURLConnection.HTTP_UNAVAILABLE:
                System.out.println("HTTP_UNAVAILABLE");
                break;
            case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
                System.out.println("HTTP_GATEWAY_TIMEOUT");
                break;
            case HttpURLConnection.HTTP_VERSION:
                System.out.println("HTTP_VERSION");
                break;
            default:
                System.out.println("Unknown status code");
        }
        if (statusCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("HTTP request failed with status code: " + statusCode);
        }
    }
}
