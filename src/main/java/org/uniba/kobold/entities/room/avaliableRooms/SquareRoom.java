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
        RoomInteractionResult result = new RoomInteractionResult(RoomInteractionResultType.DESCRIPTION);

        switch (command.getCommand().getName()) {
            case "guarda davanti":
                result.setSubject("Di fronte, oltre ai cartelli c'è l'entrata del palazzo. È protetta e chiusa, è impossibile" +
                        "entrarci, forse qualcosa di veloce la potrebbe sfondare");
                break;
            case "guarda dietro":
                result.setSubject("Guardi dietro e vedi il pub da dove sei arrivato");
                break;
            case "guarda destra":
                result.setSubject("Guardi a destra e vedi il sentiero per le fucine e per il generatore");
                break;
            case "guarda sinistra":
                result.setSubject("Guardi a sinistra e vedi il sentiero per la pista dei kart");
                break;
            case "guarda sopra":
                result.setSubject("Vedi la parete dell'enorme caverna, è da tempo che non vedi il sole...");
                break;
            case "vai bar":
            case "vai fucine":
            case "vai circuito":
            case "vai palazzo":
            case "vai generatore":
                result.setResultType(RoomInteractionResultType.MOVE);
                result.setSubject(command.getCommand().getName().split(" ")[1]);

                break;
            default:
                result.setResultType(RoomInteractionResultType.NOTHING);
        }

        return result;
    }
}
