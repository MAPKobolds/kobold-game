package org.uniba.kobold.entities.room.avaliableRooms;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.room.Room;
import org.uniba.kobold.entities.room.RoomInteractionResult;
import org.uniba.kobold.entities.room.RoomInteractionResultType;
import org.uniba.kobold.game.minigames.MiniGameInteraction;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import org.uniba.kobold.util.ColorText;
import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public final class HallwayRoom extends Room {

    public HallwayRoom() {
        super("corridoio",
                "Sei in un corridoio che si apre ad una strada. Di fronte a te vedi una " + ColorText.setTextPurple("taverna") + " preceduta" +
                    " da delle " + ColorText.setTextOrange("guardie") + ", forse loro sanno qualcosa su questo posto",
            "/img/BR.png",
                List.of(),
            Arrays.asList(
                new Command("parla guardie", Set.of()),
                new Command("vai taverna", Set.of("muoviti caverna")),
                new Command("vai caverna", Set.of("muoviti caverna"))
            )
        );
    }

    @Override
    public RoomInteractionResult executeCommand(ParserOutput command, Inventory inventory) {
        RoomInteractionResult result = new RoomInteractionResult(RoomInteractionResultType.DESCRIPTION);

        switch (command.getCommand().getName()) {
            case "guarda davanti":
                result.setSubject("Guardi davanti e vedi un gruppo di " + ColorText.setTextOrange("guardie") + ", forse ci dovresti parlare");
                break;

            case "guarda dietro":
                result.setSubject("Guardi dietro e vedi la "+ ColorText.setTextPurple("caverna") + " da cui sei arrivato");
                break;
            case "guarda destra":
            case "guarda sinistra":
            case "guarda sopra":
                result.setSubject("Una normale (Gauss shit) parete");
                break;
            case "guarda giu":
                result.setSubject("bel pavimento!");
                break;
            case "parla guardie":
                result.setSubject("guardie");
                result.setResultType(RoomInteractionResultType.PLAY);
                break;
            case "vai taverna":
                result.setSubject("taverna");
                result.setResultType(RoomInteractionResultType.MOVE);
                break;
            case "vai caverna":
                result.setSubject("caverna");
                result.setResultType(RoomInteractionResultType.MOVE);
                break;
            default:
                result.setResultType(RoomInteractionResultType.NOTHING);
        }

        return result;
    }

}
