package org.uniba.kobold.entities.room.avaliableRooms;

import org.uniba.kobold.entities.room.Room;
import org.uniba.kobold.entities.room.RoomInteractionResult;
import org.uniba.kobold.entities.room.RoomInteractionResultType;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import javax.swing.*;
import java.util.Arrays;
import java.util.Set;

public final class CircuitRoom extends Room {

    public CircuitRoom() {
        super("circuito",
                "Nel circuito ci sono molti coboldi che scommettono, rivolgendosi alla CASSA." +
                        "Alcuni hanno vinto alcune pezzi di auto e esultano, altri sono in completamente disperati per aver perso tutto.",
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
        RoomInteractionResult result = new RoomInteractionResult(RoomInteractionResultType.DESCRIPTION);

        switch (command.getCommand().getName()) {
            case "guarda davanti":
                result.setSubject("Di fronte trovi la cassiera, lì otrai fare una scommessa");
                break;
            case "guarda dietro":
                result.setSubject("Guardi dietro e vedi l'uscita che porta allo SPIAZZALE");
                break;
            case "guarda destra":
                result.setSubject("Guardi a destra e numerosi quadri e opera d'arte raffiguranti il numero due");
                break;
            case "guarda sinistra":
                result.setSubject("Guardi a sinistra e la pista, la forma ricorda il numero due");
                break;
            case "guarda sopra":
                result.setSubject("Vedi la parete dell'enorme caverna, è da tempo che non vedi il sole...");
                break;
            case "vai spiazzale":
                result.setResultType(RoomInteractionResultType.MOVE);
                result.setSubject("spiazzale");

                break;
            default:
                result.setResultType(RoomInteractionResultType.NOTHING);
        }

        return result;
    }
}
