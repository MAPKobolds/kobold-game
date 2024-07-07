package org.uniba.kobold.entities.room.avaliableRooms;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.availableItems.Cloak;
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

public final class StartingRoom extends Room {

    public StartingRoom() {
        super("caverna",
            "Ti svegli in una caverna buia, non ricordi come ci sei arrivato, ma senti un forte dolore alla testa.\n" +
                    "Guardandoti intorno è buio pesto, senti qualcosa di appiccicoso " + ColorText.setTextBlue("sotto") +" di te.\n",
            new ImageIcon("/img/BR.png"),
                List.of(new Cloak()),
            Arrays.asList(
                new Command("vai corridoio", Set.of("muoviti")),
                new Command("prendi", Set.of("raccogli", "solleva"))
            )
        );
    }

    @Override
    public RoomInteractionResult executeCommand(ParserOutput command) {
        RoomInteractionResult result = new RoomInteractionResult(RoomInteractionResultType.DESCRIPTION);

        switch (command.getCommand().getName()) {
            case "guarda giu":
                if (!Inventory.contains("mantello")) {
                    result.setSubject("Guardi giù e vedi un qualcosa di appiccicoso sotto di te di colore verde scuro, che esce da un tessuto nero, sembra un " + ColorText.setTextGreen("mantello") + " nero.");
                }else{
                    result.setSubject("Dopo aver preso il " + ColorText.setTextGreen("mantello") + ", guardi giù e vedi un essere umanoide, che purtroppo si è trovato nel posto sbagliato al momento sbagliato. Data la tua conoscenza nerd sembra un " + ColorText.setTextBlue("Coboldo") + " morto.");
                }
                break;
            case "guarda davanti":
                result.setSubject("Guardi davanti un " + ColorText.setTextPurple("corridoio") + " buio");
                break;
            case "guarda sopra":
                result.setSubject("Guardi sopra e vedi il buco da cui sei caduto");
                break;
            case "guarda dietro":
            case "guarda destra":
            case "guarda sinistra":
                result.setSubject("Vedi una parete");
                break;
            case "vai corridoio":
                result.setResultType(RoomInteractionResultType.MOVE);
                result.setSubject("corridoio");
                break;
            default:
                result.setResultType(RoomInteractionResultType.NOTHING);
        }

        return result;
    }
}
