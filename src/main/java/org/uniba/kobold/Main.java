package org.uniba.kobold;

import org.uniba.kobold.game.Game;
import org.uniba.kobold.gui.GuiHub;
import org.uniba.kobold.rest.Server;
import org.uniba.kobold.socket.WebServerSocket;

import java.io.IOException;

public class Main {
    public static void c(String[] args) throws IOException {
        Server.startServer();
        WebServerSocket.startServer();

        Game g = new Game();

        g.executeCommand("prendi mantello");
        g.executeCommand("vai corridoio");
        g.executeCommand("guarda a destra");
        g.executeCommand("parla guardie");
        g.executeCommand("vai bar");

        new GuiHub();

    }
}