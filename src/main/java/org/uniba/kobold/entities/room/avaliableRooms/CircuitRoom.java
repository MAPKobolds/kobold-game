package org.uniba.kobold.entities.room.avaliableRooms;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.room.Room;
import org.uniba.kobold.entities.room.RoomInteractionResult;
import org.uniba.kobold.entities.room.RoomInteractionResultType;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import org.uniba.kobold.util.ColorText;
import javax.swing.*;
import java.util.List;
import java.util.Set;

public final class CircuitRoom extends Room {

    public CircuitRoom() {
        super("circuito",
                "Nel circuito ci sono molti coboldi che scommettono, rivolgendosi alla " + ColorText.setTextOrange("cassiera") +
                        " <br>Alcuni hanno vinto alcune pezzi di auto e esultano, altri sono in completamente disperati per aver perso tutto.",
                "/img/BR.png",
                List.of(),
                List.of(
                    new Command("vai spiazzale", Set.of("muoviti")),
                    new Command("parla cassiera", Set.of("parla"))
                )
        );
    }

    @Override
    public RoomInteractionResult executeCommand(ParserOutput command, Inventory inventory) {
        RoomInteractionResult result = new RoomInteractionResult(RoomInteractionResultType.DESCRIPTION);

        switch (command.getCommand().getName()) {
            case "guarda davanti":
                result.setSubject("Di fronte trovi la " + ColorText.setTextOrange("cassiera") +", lì potrai fare una scommessa");
                break;
            case "guarda dietro":
                result.setSubject("Guardi dietro e vedi l'uscita che porta allo " + ColorText.setTextPurple("spiazzale"));
                break;
            case "guarda destra":
                result.setSubject("Guardi a destra e numerosi quadri e opera d'arte raffiguranti il numero due");
                break;
            case "guarda sotto":
                result.setSubject("Guardi sotto e vedi il pavimento, è sporco e pieno di cicche di sigarette");
                break;
            case "guarda sinistra":
                result.setSubject("Guardi a sinistra e la pista, la forma ricorda il numero due");
                break;
            case "guarda sopra":
                result.setSubject("Vedi la parete dell'enorme caverna, è da tempo che non vedi il sole...");
                break;
            case "vai spiazzale":
                result.setSubject("spiazzale");
                result.setResultType(RoomInteractionResultType.MOVE);
                break;
            case "parla cassiera":
                result.setSubject("cassiera");
                result.setResultType(RoomInteractionResultType.PLAY);
                break;
            default:
                result.setResultType(RoomInteractionResultType.NOTHING);
        }

        return result;
    }
}
