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

public final class PubRoom extends Room {

    public PubRoom() {
        super("taverna",
                "La " + ColorText.setTextPurple("taverna")+" è piena di coboldi,\n"+ ColorText.setTextBlue("davanti")  +" a te c'è un bancone con un coboldo " + ColorText.setTextOrange("barista") +
                        "\nsulla tua " + ColorText.setTextBlue("sinistra")  + " ci sono dei coboldi che giocano a carte, mentre" +
                        "\nsulla tua "+ ColorText.setTextBlue("destra")  +" c'è un'uscita che da su uno " + ColorText.setTextPurple("spiazzale"),
                "/img/BR.png",
                List.of(),
                Arrays.asList(
                    new Command("vai spiazzale", Set.of("muoviti")),
                    new Command("gioca blackjack", Set.of("gioca","blackjack", "carte")),
                    new Command("vai caverna", Set.of("caverna")),
                    new Command("parla barista", Set.of("barista"))
                )
        );
    }

    @Override
    public RoomInteractionResult executeCommand(ParserOutput command, Inventory inventory) {
        RoomInteractionResult result = new RoomInteractionResult(RoomInteractionResultType.DESCRIPTION);

        switch (command.getCommand().getName()) {
            case "guarda davanti":
                result.setSubject("Guardi davanti e vedi il bancone del " + ColorText.setTextOrange("barista") + ", è indaffarato a servire gli altri clienti ma avrà tempo per te");
                break;
            case "guarda dietro":
                result.setSubject("Guardi dietro e vedi le guardie che ti hanno seguito, forse è meglio non fare niente di strano");
                break;
            case "guarda destra":
                result.setSubject("Guardi a destra e un'uscita che da su uno " + ColorText.setTextPurple("spiazzale"));
                break;
            case "guarda sinistra":
                result.setSubject("Guardi a sinistra e vedi un gruppo di coboldi che giocano a " + ColorText.setTextBlue("blackjack") + ", sei abbastanza ludopatico per giocare.");
                break;
            case "guarda sopra":
                result.setSubject("bel soffitto");
                break;
            case "guarda giu":
                result.setSubject("bel pavimento");
                break;
            case "vai spiazzale":
                result.setResultType(RoomInteractionResultType.MOVE);
                result.setSubject("spiazzale");
                break;
            case "gioca blackjack":
                result.setResultType(RoomInteractionResultType.PLAY);
                result.setSubject("blackjack");
                break;
            case "vai caverna":
                result.setResultType(RoomInteractionResultType.DESCRIPTION);
                result.setSubject("Le guardie ti vedono e dicono dove vuoi andare bello? ");
                break;
            case "parla barista":
                result.setResultType(RoomInteractionResultType.PLAY);
                result.setSubject("barman");
                break;
            default:
                result.setResultType(RoomInteractionResultType.NOTHING);
        }

        return result;
    }

}
