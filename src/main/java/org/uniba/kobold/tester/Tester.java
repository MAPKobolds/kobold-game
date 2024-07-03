package org.uniba.kobold.tester;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.availableItems.Bill;
import org.uniba.kobold.game.Game;

import java.io.IOException;

public class Tester {

    public static void main(String[] args) throws IOException {

        Game game = new Game();

        for (int i = 0; i < 10; i++) {
            Inventory.addPiece(new Bill(50));
        }

        for (int i = 0; i < 10; i++) {
            Inventory.addPiece(new Bill(200));
        }

        game.executeCommand("prendi mantello");
        game.executeCommand("vai corridoio");
        game.executeCommand("parla guardie");
        game.executeCommand("vai bar");
        game.executeCommand("gioca");

        while (true) {
            String input = System.console().readLine();
            game.executeCommand(input);
        }
    }
}
