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
        switch (command.getCommand().getName()) {
            default:
                System.out.println("Comando non valido");
        }

        return new RoomInteractionResult(RoomInteractionResultType.NOTHING);
    }
}
