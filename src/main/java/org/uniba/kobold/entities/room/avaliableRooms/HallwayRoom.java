package org.uniba.kobold.entities.room.avaliableRooms;

import org.uniba.kobold.entities.InteractionResult;
import org.uniba.kobold.entities.character.availableCharacters.TwinGuards;
import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.room.Room;
import org.uniba.kobold.entities.room.RoomInteractionResult;
import org.uniba.kobold.entities.room.RoomInteractionResultType;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import javax.swing.*;
import java.util.Arrays;
import java.util.Set;

public final class HallwayRoom extends Room {

    public HallwayRoom() {
        super("corridoio",
                "Sei in un corridoio che si apre ad una strada. Di fronte a te vedi un BAR preceduto" +
                    " da delle guardie, forse loro sanno qualcosa su questo posto",
            new ImageIcon("/img/BR.png"),
            Arrays.asList(),
            Arrays.asList(new TwinGuards()),
            Arrays.asList(new Command("parla guardie", Set.of()))
        );
    }

    @Override
    public RoomInteractionResult executeCommand(ParserOutput command) {
        RoomInteractionResult result = new RoomInteractionResult(RoomInteractionResultType.NOTHING);

        switch (command.getCommand().getName()) {
            case "guarda davanti":
                System.out.println("Guardi davanti e vedi un gruppo di guardie, forse ci dovresti parlare");
                break;
            case "guarda dietro":
                System.out.println("Guardi dietro e vedi la caverna da cui sei arrivato");
                break;
            case "guarda destra":
            case "guarda sinistra":
            case "guarda sopra":
                System.out.println("Una normale (Gauss shit) parete");
                break;
            case "parla guardie":
                InteractionResult interactionResult = getCharacterByName("guardie").interact(Inventory.contains("mantello"));

                if(interactionResult == InteractionResult.SUCCESSFUL) {
                    result.setResultType(RoomInteractionResultType.MOVE);
                    result.setSubject("bar");
                }

                break;
            default:
                System.out.println("Comando non valido");
        }

        return result;
    }

}
