package org.uniba.kobold.tester;

import org.uniba.kobold.game.Game;

import java.io.IOException;

/**
 * The type Tester.
 */
public class Tester {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {

        Game game = new Game("Paolo");


        while (true) {
            String input = System.console().readLine();

            System.out.println(game.executeCommand(input).getDescription());
            if (input.equals("sussylabaka")) {
                break;
            }
        }
    }
}
