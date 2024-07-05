package org.uniba.kobold;

import org.uniba.kobold.rest.Server;
import org.uniba.kobold.socket.WebServerSocket;
import java.io.IOException;
import org.uniba.kobold.guiRef.GuiHubRef;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Server.startServer();
        WebServerSocket.startServerA();
        WebServerSocket.startServerB();
        //Game g = new Game();
        EventQueue.invokeLater(() -> new GuiHubRef().setVisible(true));
    }
}