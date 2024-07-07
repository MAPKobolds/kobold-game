package org.uniba.kobold.tester;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.availableItems.*;
import org.uniba.kobold.game.Game;

import java.io.IOException;

public class Tester {

    public static void main(String[] args) throws IOException {

        Game game = new Game("Paolo");

        Inventory.addMoney(800);
        Inventory.addPiece(new SteeringWheel());
        Inventory.addPiece(new CarBody());
        Inventory.addPiece(new Engine());


        while (true) {
            String input = System.console().readLine();
            game.executeCommand(input);
            if (input.equals("sussylabaka")) {
                break;
            }
        }
    }
}
