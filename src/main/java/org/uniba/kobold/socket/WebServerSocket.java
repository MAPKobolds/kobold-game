package org.uniba.kobold.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServerSocket {
    private static final int PORT = 4200;
    static final int MAX_USERS = 5;

    public static void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT, MAX_USERS);
            System.out.println("SERVER STARTED at http://localhost:" + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                HttpPage page = new HttpPage(socket);

                page.renderPage("src/main/java/org/uniba/kobold/socket/page.html");
            }

        } catch(IOException ex){
            System.out.println("CANNOT START SERVER AT PORT: " + PORT);
        }


    }

}
