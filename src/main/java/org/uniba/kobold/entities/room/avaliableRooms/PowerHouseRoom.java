package org.uniba.kobold.entities.room.avaliableRooms;

import org.uniba.kobold.entities.room.Room;
import org.uniba.kobold.entities.room.RoomInteractionResult;
import org.uniba.kobold.entities.room.RoomInteractionResultType;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import javax.swing.*;
import java.util.Arrays;
import java.util.Set;

public final class PowerHouseRoom extends Room {

    public PowerHouseRoom() {
        super("generatore",
                "L'edificio contenente il generatore è il più pulito e ordinato di questo inferno. Su tutte le pareti sono stati piazzati dei motori di autovetture " +
                        "che producono energia. \n " +
                        "L'unico essere in questo posto, seduto ad una scrivania è un coboldo, sembra dirigere questo posto dal suo computer",
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
                System.out.println("Di fronte, c'è il direttore coboldo, sembra molto preso dallo schermo del " +
                        "suo PC");
                break;
            case "guarda dietro":
                System.out.println("Guardi dietro e vedi l'uscita che da sullo SPIAZZALE");
                break;
            case "guarda destra":
            case "guarda sinistra":
                System.out.println("Le pareti sono piene di motori, c'è un rumore assordante");
                break;
            case "guarda sopra":
                System.out.println("Un normale soffitto, anch'esso pieno di motori");
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