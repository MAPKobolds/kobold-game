package org.uniba.kobold;

import org.uniba.kobold.gui.GuiHub;
import org.uniba.kobold.rest.Server;

public class Main {
    public static void main(String[] args) {
        Server.startServer();

        new GuiHub();
    }
}