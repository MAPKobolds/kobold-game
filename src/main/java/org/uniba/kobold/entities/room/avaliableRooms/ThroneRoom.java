package org.uniba.kobold.entities.room.avaliableRooms;

import org.uniba.kobold.entities.room.Room;
import org.uniba.kobold.entities.room.RoomInteractionResult;
import org.uniba.kobold.entities.room.RoomInteractionResultType;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import javax.swing.*;
import java.util.Arrays;
import java.util.Set;

public final class ThroneRoom extends Room {

    public ThroneRoom() {
        super("trono",
                "Dopo tanta fatica sei arrivato dal capo di questo posto infernale. Il coboldo più grande che tu abbia mai visto è sul suo trono di " +
                        "rottami, ti guarda dall'alto verso il basso aspettando la tua mossa",
                new ImageIcon("/img/BR.png"),
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList(
                    new Command("vai palazzo", Set.of("muoviti"))
                )
        );
    }

    @Override
    public RoomInteractionResult executeCommand(ParserOutput command) {
        switch (command.getCommand().getName()) {
            case "guarda davanti":
                System.out.println("Di fronte, c'è l'enorme trono di cianfrusaglie del re coboldo." +
                        "\nIl re ti sta fissando con aria quasi scocciata");
                break;
            case "guarda dietro":
                System.out.println("Guardi dietro e vedi il corridoio del PALAZZO");
                break;
            case "guarda destra":
            case "guarda sinistra":
                System.out.println("La stanza è vuota");
                break;
            case "guarda sopra":
                System.out.println("Il palazzo reale ha un soffitto che quasi ricorda quello di un palazzo comunale...");
                break;
            default:
                System.out.println("Comando non valido");
        }

        return new RoomInteractionResult(RoomInteractionResultType.NOTHING);
    }
}
