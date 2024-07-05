package org.uniba.kobold.tester;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.game.Game;

import java.io.IOException;

public class Tester {

    public static void main(String[] args) throws IOException {

        Game game = new Game();

        Inventory.addMoney(800);
        game.executeCommand("prendi mantello");
        game.executeCommand("vai corridoio");
        game.executeCommand("parla guardie");
        game.executeCommand("vai taverna");
        game.executeCommand("vai spiazzale");
        game.executeCommand("vai fucine");

        while (true) {
            String input = System.console().readLine();
            game.executeCommand(input);
            if (input.equals("sussylabaka")) {
                break;
            }
        }
    }
}
