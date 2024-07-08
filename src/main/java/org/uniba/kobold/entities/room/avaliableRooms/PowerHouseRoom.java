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

public final class PowerHouseRoom extends Room {

    public PowerHouseRoom() {
        super("generatore",
                "L'edificio contenente il generatore è il più pulito e ordinato di questo inferno.<br>Su tutte le pareti sono stati piazzati dei motori di autovetture " +
                        "che producono energia. <br>" +
                        "L'unico essere in questo posto, seduto ad una scrivania è un coboldo,<br>sembra dirigere questo posto dal suo computer è " + ColorText.setTextBlue("davanti") + " a te",
                "/img/rooms/powerstation.jpg",
                List.of(),
                List.of(
                        new Command("vai spiazzale", Set.of("muoviti")),
                        new Command("parla direttore", Set.of("direttore")),
                        new Command("aiuta direttore", Set.of("aiuta", "direttore"))
                )
        );
    }

    @Override
    public RoomInteractionResult executeCommand(ParserOutput command, Inventory inventory) {
        RoomInteractionResult result = new RoomInteractionResult(RoomInteractionResultType.DESCRIPTION);

        switch (command.getCommand().getName()) {
            case "guarda davanti":
                result.setSubject("Di fronte, c'è il " + ColorText.setTextOrange("direttore") + " coboldo , sembra molto preso dallo schermo del " +
                        "suo PC");
                break;
            case "guarda dietro":
                result.setSubject("Guardi dietro e vedi l'uscita che da sullo " + ColorText.setTextPurple("spiazzale"));
                break;
            case "guarda destra":
            case "guarda sinistra":
                result.setSubject("Le pareti sono piene di motori, c'è un rumore assordante");
                break;
            case "guarda giu":
                result.setSubject("Guardi sotto e vedi un pavimento sporco e pieno di cavi elettrici");
                break;
            case "guarda sopra":
                result.setSubject("Un normale soffitto, anch'esso pieno di motori");
                break;
            case "vai spiazzale":
                result.setResultType(RoomInteractionResultType.MOVE);
                result.setSubject("spiazzale");
                break;
            case "parla direttore":
                result.setSubject("Il coboldo direttore ti guarda e ti dice: 'Non hai niente da fare qui, vai via!' <br>" +
                        "vedi però che sul suo pc sta cercando da oltre 10 minuti di risolvere dei quiz di film <br>sarebbe meglio che lo "
                        + ColorText.setTextBlue("aiuti"));
                break;
            case "aiuta direttore":
                result.setSubject("trivia");
                result.setResultType(RoomInteractionResultType.PLAY);
                break;
            default:
                result.setResultType(RoomInteractionResultType.NOTHING);
        }

        return result;
    }
}
