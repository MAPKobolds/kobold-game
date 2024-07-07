package org.uniba.kobold.tester;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.availableItems.*;
import org.uniba.kobold.game.Game;

import java.io.IOException;

public class Tester {

    public static void main(String[] args) throws IOException {

        Game game = new Game("Paolo");
        game.getInventory().addPiece(new Car());
        game.executeCommand("prendi mantello");
        game.executeCommand("vai corridoio");
        game.executeCommand("parla guardie");
        game.executeCommand("1");
        game.executeCommand("vai taverna");
        game.executeCommand("vai spiazzale");


        while (true) {
            String input = System.console().readLine();
            game.executeCommand(input);
            if (input.equals("sussylabaka")) {
                break;
            }
        }
    }
}
