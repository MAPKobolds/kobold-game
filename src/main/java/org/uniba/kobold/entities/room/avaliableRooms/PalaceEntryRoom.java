package org.uniba.kobold.entities.room.avaliableRooms;

import org.uniba.kobold.entities.room.Room;
import org.uniba.kobold.entities.room.RoomInteractionResult;
import org.uniba.kobold.entities.room.RoomInteractionResultType;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import javax.swing.*;
import java.util.Arrays;
import java.util.Set;

public final class PalaceEntryRoom extends Room {

    public PalaceEntryRoom() {
        super("palazzo",
                "Dopo aver sfondato il cancello del palazzo hai ucciso numerose guardie, nonostante ciò la macchina è integra." +
                        "Il corridoio è illuminato da torce, in fondo puoi vedere in lontananza il TRONO",
                new ImageIcon("/img/BR.png"),
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList(
                    new Command("vai spiazzale", Set.of("muoviti")),
                    new Command("vai trono", Set.of("muoviti"))
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
                result.setSubject("Guardi dietro e vedi i cadaveri dei coboldi travolti e l'uscita verso" +
                        "lo SPIAZZALE, hai fatto un bel casino!");
                break;
            case "guarda destra":
            case "guarda sinistra":
                result.setSubject("Le pareti sono piene di TORCE per illuminare");
                break;
            case "guarda sopra":
                result.setSubject("Il palazzo reale ha un soffitto che quasi ricorda quello di un palazzo comunale...");
                break;
            case "vai spiazzale":
            case "vai trono":
                result.setResultType(RoomInteractionResultType.MOVE);
                result.setSubject(command.getCommand().getName().split(" ")[0]);

                break;
            default:
                result.setResultType(RoomInteractionResultType.NOTHING);
        }

        return result;
    }
}
