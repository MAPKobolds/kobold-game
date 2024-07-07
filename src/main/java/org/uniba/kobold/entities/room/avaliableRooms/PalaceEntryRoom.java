package org.uniba.kobold.entities.room.avaliableRooms;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.availableItems.FireMaul;
import org.uniba.kobold.entities.room.Room;
import org.uniba.kobold.entities.room.RoomInteractionResult;
import org.uniba.kobold.entities.room.RoomInteractionResultType;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import org.uniba.kobold.util.ColorText;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public final class PalaceEntryRoom extends Room {

    public PalaceEntryRoom() {
        super("palazzo",
                "Dopo aver sfondato il cancello del palazzo hai ucciso numerose guardie, nonostante ciò la macchina è integra.\n" +
                        "Il corridoio è illuminato da torce, in fondo puoi vedere in lontananza \nIl " + ColorText.setTextPurple("trono") + " del re coboldo",
                "/img/BR.png",
                List.of(new FireMaul()),
                Arrays.asList(
                    new Command("vai spiazzale", Set.of("muoviti")),
                    new Command("vai trono", Set.of("muoviti"))
                )
        );
    }

    @Override
    public RoomInteractionResult executeCommand(ParserOutput command, Inventory inventory) {
        RoomInteractionResult result = new RoomInteractionResult(RoomInteractionResultType.DESCRIPTION);

        switch (command.getCommand().getName()) {
            case "guarda davanti":
                result.setSubject("Di fronte, sta il trono del re coboldo, ci sono delle porte che ti separano dal " +
                        ColorText.setTextPurple("trono") + ", hai la tua " + ColorText.setTextGreen("macchina") + " con te un altro lavoro per lei" );
                break;

            case "guarda dietro":
                result.setSubject("Guardi dietro e vedi i cadaveri dei coboldi travolti e l'uscita verso " +
                        " lo " + ColorText.setTextPurple("spiazzale") + ", hai fatto un bel casino!");
                break;
            case "guarda destra":
            case "guarda sinistra":
                result.setSubject("Le pareti sono piene di "+ ColorText.setTextGreen("magli") +" di fuoco per illuminare");
                break;
            case "guarda sopra":
                result.setSubject("Il palazzo reale ha un soffitto che quasi ricorda quello di un palazzo comunale...");
                break;
            case "guarda giu":
                result.setSubject("Il pavimento è sporco di sangue e di pezzi di coboldi");
                break;
            case "vai spiazzale":
            case "vai trono":
                result.setResultType(RoomInteractionResultType.MOVE);
                result.setSubject(command.getCommand().getName().split(" ")[1]);
                break;
            default:
                result.setResultType(RoomInteractionResultType.NOTHING);
        }

        return result;
    }
}
