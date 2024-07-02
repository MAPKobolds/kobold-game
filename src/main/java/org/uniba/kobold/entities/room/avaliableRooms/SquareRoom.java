package org.uniba.kobold.entities.room.avaliableRooms;

import org.uniba.kobold.entities.room.Room;
import org.uniba.kobold.entities.room.RoomInteractionResult;
import org.uniba.kobold.entities.room.RoomInteractionResultType;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import javax.swing.*;
import java.util.Arrays;
import java.util.Set;

public final class SquareRoom extends Room {

    public SquareRoom() {
        super("spiazzale",
                "Lo spiazzale si trova all'uscita del BAR ed è un posto di ritrovo dei coboldi, circondato da altri palazzi abitati da altri coboldi." +
                        "Di fronte a te noti dei cartelli che indicano dei luoghi di interesse: " +
                        "\nFUCINE" +
                        "\nPISTA" +
                        "\nPALAZZO" +
                        "\nGENERATORE",
                new ImageIcon("/img/BR.png"),
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList(
                    new Command("vai bar", Set.of("muoviti")),
                    new Command("vai fucine", Set.of("muoviti")),
                    new Command("vai circuito", Set.of("muoviti")),
                    new Command("vai palazzo", Set.of("muoviti")),
                    new Command("vai generatore", Set.of("muoviti"))
                )
        );
    }

    @Override
    public RoomInteractionResult executeCommand(ParserOutput command) {
        switch (command.getCommand().getName()) {
            case "guarda davanti":
                System.out.println("Di fronte, oltre ai cartelli c'è l'entrata del palazzo. È protetta e chiusa, è impossibile" +
                        "entrarci, forse qualcosa di veloce la potrebbe sfondare");
                break;
            case "guarda dietro":
                System.out.println("Guardi dietro e vedi il pub da dove sei arrivato");
                break;
            case "guarda destra":
                System.out.println("Guardi a destra e vedi il sentiero per le fucine e per il generatore");
                break;
            case "guarda sinistra":
                System.out.println("Guardi a sinistra e vedi il sentiero per la pista dei kart");
                break;
            case "guarda sopra":
                System.out.println("Vedi la parete dell'enorme caverna, è da tempo che non vedi il sole...");
                break;
            default:
                System.out.println("Comando non valido");
        }

        return new RoomInteractionResult(RoomInteractionResultType.NOTHING);
    }
}
