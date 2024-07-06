package org.uniba.kobold;

import org.uniba.kobold.game.Game;
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
        EventQueue.invokeLater(() -> new GuiHubRef().setVisible(true));
        /*Game g = new Game();
        g.executeCommand("prendi mantello");*/
    }
}