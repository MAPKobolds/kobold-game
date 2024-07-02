package org.uniba.kobold.entities.room.avaliableRooms;

import org.uniba.kobold.entities.room.Room;
import org.uniba.kobold.entities.room.RoomInteractionResult;
import org.uniba.kobold.entities.room.RoomInteractionResultType;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;

import javax.swing.*;
import java.util.Arrays;
import java.util.Set;

public final class PubRoom extends Room {

    public PubRoom() {
        super("bar",
                "Il bar è pieno di coboldi, di fronte a te c'è un bancone con un coboldo barista. " +
                        "Sulla tua sinistra ci sono dei coboldi che giocano a carte" +
                        "Sulla tua destra c'è un'uscita che da su uno SPIAZZALE",
                new ImageIcon("/img/BR.png"),
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList(
                    new Command("vai spiazzale", Set.of("muoviti"))
                )
        );
    }

    @Override
    public RoomInteractionResult executeCommand(ParserOutput command) {
        RoomInteractionResult result = new RoomInteractionResult(RoomInteractionResultType.NOTHING);

        switch (command.getCommand().getName()) {
            case "guarda davanti":
                System.out.println("Guardi davanti e vedi il bancone del barista, è indaffarato a servire gli altri clienti ma avrà tempo per te");
                break;
            case "guarda dietro":
                System.out.println("Guardi dietro e vedi il corridoio da dove sei arrivato");
                break;
            case "guarda destra":
                System.out.println("Guardi a destra e un'uscita che da su uno SPIAZZALE");
                break;
            case "guarda sinistra":
                System.out.println("Guardi a sinistra e vedi un gruppo di coboldi che giocano a blackjack, sei abbastanza ludopatico per giocare.");
                break;
            case "guarda sopra":
                System.out.println("Una normale (Gauss shit) parete in legno");
                break;
            case "vai spiazzale":
                result.setResultType(RoomInteractionResultType.MOVE);
                result.setSubject("spiazzale");

                break;
            default:
                System.out.println("Comando non valido");
        }

        return result;
    }

}
