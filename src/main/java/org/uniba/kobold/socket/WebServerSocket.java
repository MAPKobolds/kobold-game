package org.uniba.kobold.socket;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServerSocket {
    private static final int PORT_A = 4200;
    private static final int PORT_B = 4300;

    static final int MAX_USERS = 5;

    public static void startServerA() {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(PORT_A, MAX_USERS);
                System.out.println("SERVER STARTED at http://localhost:" + PORT_A);

                while (true) {
                    Socket socket = serverSocket.accept();
                    HttpPage page = new HttpPage(socket);

                    page.renderPage(new File("src/main/java/org/uniba/kobold/socket/page.html"));
                }

            } catch(IOException ex){
                System.out.println("CANNOT START SERVER AT PORT: " + PORT_A);
            }
        }).start();
    }

    public static void startServerB() {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(PORT_B, MAX_USERS);
                System.out.println("SERVER STARTED at http://localhost:" + PORT_B);

                while (true) {
                    Socket socket = serverSocket.accept();
                    HttpPage page = new HttpPage(socket);

                    page.renderPage(PostPage.getPostPage());
                }

            } catch(IOException ex){
                System.out.println("CANNOT START SERVER AT PORT: " + PORT_B);
            }
        }).start();
    }

}
