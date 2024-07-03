package org.uniba.kobold;


import org.uniba.kobold.gui.GuiHub;
import org.uniba.kobold.rest.Server;
import org.uniba.kobold.tester.Tester;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server.startServer();

        Tester.main(args);

        new GuiHub();

    }
}