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
        switch (command.getCommand().getName()) {
            default:
                System.out.println("Comando non valido");
        }

        return new RoomInteractionResult(RoomInteractionResultType.NOTHING);
    }
}
