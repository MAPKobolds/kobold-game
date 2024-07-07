package org.uniba.kobold.entities.room.avaliableRooms;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.room.Room;
import org.uniba.kobold.entities.room.RoomInteractionResult;
import org.uniba.kobold.entities.room.RoomInteractionResultType;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import org.uniba.kobold.util.ColorText;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public final class ThroneRoom extends Room {

    public ThroneRoom() {
        super("trono",
                "Dopo tanta fatica sei arrivato dal capo di questo posto infernale. Il coboldo più grande che tu abbia mai visto è sul suo trono di " +
                        "rottami è il " + ColorText.setTextOrange("re") +", ti guarda dall'alto verso il basso aspettando la tua mossa",
                "/img/BR.png",
                List.of(),
                List.of(
                        new Command("vai palazzo", Set.of("muoviti")),
                        new Command("parla re", Set.of("parla"))
                )
        );
    }

    @Override
    public RoomInteractionResult executeCommand(ParserOutput command, Inventory inventory) {
        RoomInteractionResult result = new RoomInteractionResult(RoomInteractionResultType.DESCRIPTION);

        switch (command.getCommand().getName()) {
            case "guarda davanti":
                result.setSubject("Di fronte, c'è l'enorme trono di cianfrusaglie del re coboldo." +
                        "\nIl re ti sta fissando con aria quasi scocciata");
                break;
            case "guarda dietro":
                result.setSubject("Guardi dietro e vedi il corridoio del " + ColorText.setTextPurple("palazzo") + ", " +
                        "le guardie sono state tutte uccise, non c'è più nessuno a difendere il re coboldo");
                break;
            case "guarda destra":
            case "guarda sinistra":
                result.setSubject("montagne di oro e gioielli sono ammassati ai lati del trono, " +
                        "sembra che il re coboldo non abbia problemi di soldi...");
                break;
            case "guarda sopra":
                result.setSubject("Il palazzo reale ha un soffitto che quasi ricorda quello di un palazzo comunale...");
                break;
            case "guarda sotto":
                result.setSubject("un pavimento tesserato di marmo, " +
                        "sembra che il re coboldo abbia un debole per i pavimenti lussuosi");
                break;
            case "vai palazzo":
                result.setSubject("palazzo");
                result.setResultType(RoomInteractionResultType.MOVE);
                break;
            case "parla re":
                result.setSubject("re");
                result.setResultType(RoomInteractionResultType.PLAY);
                break;
            default:
                result.setResultType(RoomInteractionResultType.NOTHING);
        }
        return result;
    }
}
