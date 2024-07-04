package org.uniba.kobold;

import org.uniba.kobold.game.Game;
import org.uniba.kobold.gui.GuiHub;
import org.uniba.kobold.rest.Server;
import org.uniba.kobold.socket.WebServerSocket;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server.startServer();
        WebServerSocket.startServerA();
        WebServerSocket.startServerB();
        new GuiHub();
    }
}