package org.uniba.kobold.tester;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.availableItems.*;
import org.uniba.kobold.game.Game;

import java.io.IOException;

public class Tester {

    public static void main(String[] args) throws IOException {

        Game game = new Game("Paolo");


        while (true) {
            String input = System.console().readLine();
            game.executeCommand(input);
            if (input.equals("sussylabaka")) {
                break;
            }
        }
    }
}
