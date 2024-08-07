package org.uniba.kobold;

import org.uniba.kobold.rest.Server;
import org.uniba.kobold.socket.WebServerSocket;

import java.io.File;
import java.io.IOException;
import org.uniba.kobold.gui.GuiHub;
import org.uniba.kobold.util.DirCreator;

import java.awt.*;

/**
 * The type Main.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {

        if (!DirCreator.createDir("src/main/resources/saves")) {
            throw new Error("Errore nella creazione della cartella saves");
        }

        Server.startServer();
        WebServerSocket.startServerA();
        WebServerSocket.startServerB();

        EventQueue.invokeLater(() -> new GuiHub().setVisible(true));
    }
}