package org.uniba.kobold.entities.room.avaliableRooms;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.availableItems.Car;
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

public final class ForgeRoom extends Room {

    public ForgeRoom() {
        super("fucine",
                "Le fucine sono un posto molto caldo,vedi dei pezzi di metallo su un "+ ColorText.setTextBlue("rullo") +" fra cui delle carrozzerie di auto che vengono fusi.\nSul nastro ci sono pezzi di carrozzeria." +
                        "Ti sembra di aver visto qualcosa di interessante sul " + ColorText.setTextBlue("rullo") + ",\nvedi a destra una " + ColorText.setTextBlue("forgia") + " per la fusione dei pezzi di metallo forse potresti creare qualcosa di utile",
                new ImageIcon("/img/BR.png"),
                List.of(),
                Arrays.asList(
                    new Command("vai spiazzale", Set.of("muoviti")),
                    new Command("guarda rullo", Set.of("rullo")),
                    new Command("guarda forgia", Set.of("forgia")),
                    new Command("usa forgia", Set.of("usa forgia"))
                )
        );
    }

    @Override
    public RoomInteractionResult executeCommand(ParserOutput command) {
        RoomInteractionResult result = new RoomInteractionResult(RoomInteractionResultType.DESCRIPTION);

        switch (command.getCommand().getName()) {
            case "guarda davanti":
                result.setSubject("Di fronte, vedi un " + ColorText.setTextBlue("rullo") +" dove stanno passando numerosi pezzi di auto, forse potrebbe esserci" +
                        "qualcosa di familiare attraverso quella ferraglia");
                break;
            case "guarda dietro":
                result.setSubject("Guardi dietro e vedi l'uscita che porta allo " + ColorText.setTextPurple("spiazzale"));
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
            case "guarda rullo":
                if (!Inventory.contains("carrozzeria")){
                    result.setSubject("rullo");
                    result.setResultType(RoomInteractionResultType.PLAY);
                } else {
                    result.setSubject(ColorText.setTextRed("Hai già preso la carrozzeria, non c'è più nulla qui"));
                }
                break;
            case "guarda forgia":
                result.setSubject("Vedi una grande forgia, potresti creare qualcosa di utile, c'è scritto che per "+ ColorText.setTextBlue("usa") +"rla devi avere tutti i pezzi necessari per creare un'auto : " + ColorText.setTextGreen("carrozzeria motore  volante"));
                break;
            case "usa forgia":
                if (Inventory.contains("carrozzeria") && Inventory.contains("motore") && Inventory.contains("volante")){
                    Inventory.removePiece("carrozzeria");
                    Inventory.removePiece("motore");
                    Inventory.removePiece("volante");
                    Inventory.addPiece(new Car());
                    result.setSubject("Hai creato la tua " + ColorText.setTextGreen("auto") + "!");
                } else {
                    result.setSubject(ColorText.setTextRed("Non hai tutti i pezzi necessari per creare un auto"));
                }
                break;
            default:
                result.setResultType(RoomInteractionResultType.NOTHING);
        }

        return result;
    }
}
