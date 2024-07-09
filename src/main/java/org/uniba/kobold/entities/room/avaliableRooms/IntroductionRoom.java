package org.uniba.kobold.entities.room.avaliableRooms;

import org.javatuples.Pair;
import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.availableItems.Cloak;
import org.uniba.kobold.entities.room.Room;
import org.uniba.kobold.entities.room.RoomInteractionResult;
import org.uniba.kobold.entities.room.RoomInteractionResultType;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import org.uniba.kobold.util.ColorText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public final class IntroductionRoom extends Room {
    int continueCounter = 0;
    private List<Pair<String, String>> slides = List.of(
            Pair.with("/img/rooms/falling.jpg", "Sei andato in un post ma sei caduto in un buco di merda")
    );


    public IntroductionRoom() {
        super("introduzione",
                "Guardando fuori dalla finestra vedi che ti stanno fregando la macchina (Work in Progress) (continua per andare avantis).",
                "/img/rooms/cartheft.jpg",
                List.of(new Cloak()),
                Arrays.asList(
                    new Command("continua", Set.of("avanti"))
                )
        );
    }

    @Override
    public RoomInteractionResult executeCommand(ParserOutput command, Inventory inventory) {
        RoomInteractionResult result = new RoomInteractionResult(RoomInteractionResultType.CHANGE_BACKGROUND);

        if (command.getCommand().getName().equals("continua")) {
            if(this.continueCounter == 1) {
                result.setSubject("caverna");
                result.setResultType(RoomInteractionResultType.MOVE);
            } else {
                this.setBackgroundImage(this.slides.get(this.continueCounter).getValue0());
                result.setSubject(this.slides.get(continueCounter).getValue1());

                continueCounter++;
            }
        } else {
            result.setResultType(RoomInteractionResultType.NOTHING);
        }

        return result;
    }
}
