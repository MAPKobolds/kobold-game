package org.uniba.kobold.entities.room.avaliableRooms;

import org.uniba.kobold.entities.room.Room;
import org.uniba.kobold.entities.room.RoomInteractionResult;
import org.uniba.kobold.entities.room.RoomInteractionResultType;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import javax.swing.*;
import java.util.Arrays;
import java.util.Set;

public final class ForgeRoom extends Room {

    public ForgeRoom() {
        super("fucine",
                "Le fucine sono un posto molto caldo, pezzi di metallo  su un RULLO fra cui carrozzerie di auto vengono fusi. Sul nastro ci sono pezzi di carrozzeria." +
                        "Ti sembra di aver visto qualcosa di interessante sul RULLO",
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
                result.setSubject("Di fronte, vedi un RULLO dove stanno passando numerosi pezzi di auto, forse potrebbe esserci" +
                        "qualcosa di familiare attraverso quella ferraglia");
                break;
            case "guarda dietro":
                result.setSubject("Guardi dietro e vedi l'uscita che porta allo SPIAZZALE");
                break;
            case "guarda destra":
                result.setSubject("Guardi a destra e vedi numerosi coboldi che trasportano pezzi di metallo");
                break;
            case "guarda sinistra":
                result.setSubject("Guardi a destra e vedi una montagna di pezzi di ricambio per auto");
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
