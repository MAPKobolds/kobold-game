package org.example.kobold.rest;

import org.example.kobold.rest.controllers.PlayerController;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private static final int PORT = 8000;

    public static void startServer() {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(PORT).build();

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, getControllers());

        new Thread(() -> {
            try {
                server.start();
                System.out.println(String.format("SERVER STARTED at http://localhost:%d", PORT));

                System.in.read();
                server.shutdown();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();

    }

    private static ResourceConfig getControllers() {
        ResourceConfig config = new ResourceConfig(new Class[] {
            PlayerController.class
        });

        return config;
    }

}
