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
            Pair.with("/img/rooms/falling.jpg", "Il giorno successivo segui il Gps della tua auto e mentre stai sopra al punto segnato, ti accorgi che il terreno cede sotto i tuoi piedi " + ColorText.setTextBlue("continua") + " per continuare"),
            Pair.with("/img/rooms/tutorial.jpg", "Salve questo è il tutorial di Kobold, un gioco testuale in cui dovrai risolvere enigmi e rompicapi per poter proseguire. Qui ti elenchiamo i comandi generali:<br>"
                    + ColorText.setTextBlue("guarda") + " per guardare in una direzione sopra/sotto/giu... <br>" +
                    ColorText.setTextBlue("prendi") + " per prendere un oggetto <br>" +
                    ColorText.setTextBlue("parla") + " per parlare con un personaggio <br>" +
                    ColorText.setTextBlue("soldi") + " per vedere quanti soldi hai <br>" +
                    ColorText.setTextBlue("aiuto") + " per far riapparire la descrizione della stanza <br>" +
                    ColorText.setTextBlue("ispeziona") + " per vedere cosa fa un determinato item <br>" +
                    ColorText.setTextBlue("continua") + " per continuare")
    );


    public IntroductionRoom() {
        super("introduzione",
                "Era una giornata come le altre a Molfetta, dopo un lungo giorno vuoi andare alla pizzeria per prendere una siciliana (\"la pizza\") " +
                        "esci dalla pizzeria con le pizze in mano e vedi che ti stanno rubando la macchina ... Aspe Cosa?! " + ColorText.setTextBlue("continua") + " per continuare",
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
            if(this.continueCounter == 2) {
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
