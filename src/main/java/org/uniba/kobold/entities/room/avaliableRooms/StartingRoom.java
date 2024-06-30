package org.uniba.kobold.entities.room.avaliableRooms;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.room.Room;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;

import javax.swing.*;
import java.util.Arrays;
import java.util.Set;

public class StartingRoom extends Room {

    public StartingRoom() {
        super("caverna",
            "Ti svegli in una caverna buia, non ricordi come ci sei arrivato, ma senti un forte dolore alla testa.\n" +
                    "Guardandoti intorno è buio pesto, senti qualcosa di appiccicoso sotto di te.\n",
            new ImageIcon("/img/BR.png"),
            Arrays.asList(),
            Arrays.asList(),
            Arrays.asList(
                new Command("vai corridoio", Set.of("muoviti"))
            )
        );
    }


    @Override
    public void executeCommand(ParserOutput command) {
        switch (command.getCommand().getName()) {

            case "guarda giù":
                System.out.println("Guardi giù e vedi un qualcosa di appiccicoso sotto di te di colore verde scuro, che esce da un tessuto nero, sembra un mantello nero.");
                break;
            case "guarda davanti":
                System.out.println("Guardi davanti un corridoio buio");
                break;
            case "guarda dietro":
                System.out.println("Guardi dietro e vedi una parete");
                break;
            case "guarda sopra":
                System.out.println("Guardi sopra e vedi il buco da cui sei caduto");
                break;
            case "guarda destra":
                System.out.println("Guardi a destra e vedi una parete");
                break;
            case "guarda sinistra":
                System.out.println("Guardi a sinistra e vedi una parete");
                break;
            case "prendi":
                if (command.getItem().getName().equals("mantello")) {
                    System.out.println("Hai preso il mantello");
                    Inventory.addPiece(command.getItem());
                } else {
                    System.out.println("Non c'è niente da prendere");
                }
                break;
            case "usa":
                if (command.getItem().getName().equals("mantello")) {
                    System.out.println("Hai messo il mantello");
                } else {
                    System.out.println("Non puoi usare questo oggetto");
                }
                break;
            case "ispeziona":
                if (command.getItem().getName().equals("mantello")) {
                    System.out.println(command.getItem().getDescription());
                } else {
                    System.out.println("Non c'è niente da ispezionare");
                }
                break;
            default:
                System.out.println("Comando non valido");
        }
    }

}
