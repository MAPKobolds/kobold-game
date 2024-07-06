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

public final class SquareRoom extends Room {

    public SquareRoom() {
        super("spiazzale",
                "Lo spiazzale si trova all'uscita del BAR ed è un posto di ritrovo dei coboldi, circondato da altri palazzi abitati da altri coboldi.\n" +
                        "Di fronte a te noti dei cartelli che indicano dei luoghi di interesse: " +
                        ColorText.setTextPurple("\nfucine") +
                        ColorText.setTextPurple("\npiste") +
                        ColorText.setTextPurple("\ngeneratore") +
                        ColorText.setTextPurple("\npalazzo reale"),
                new ImageIcon("/img/BR.png"),
                List.of(),
                List.of(),
                Arrays.asList(
                    new Command("vai taverna", Set.of("taverna")),
                    new Command("vai fucine", Set.of("fucine")),
                    new Command("vai circuito", Set.of("vai piste","piste")),
                    new Command("vai palazzo", Set.of("vai palazzo reale","palazzo reale","palazzo")),
                    new Command("vai generatore", Set.of("vai generatore","generatore"))
                )
        );
    }

    @Override
    public RoomInteractionResult executeCommand(ParserOutput command) {
        RoomInteractionResult result = new RoomInteractionResult(RoomInteractionResultType.DESCRIPTION);

        switch (command.getCommand().getName()) {
            case "guarda davanti":
                result.setSubject("Di fronte, oltre ai cartelli c'è l'entrata del " + ColorText.setTextPurple("palazzo\n")+". È protetta e chiusa, è impossibile entrarci, " +
                        ColorText.setTextBlue("forse qualcosa di veloce la potrebbe sfondare"));
                break;
            case "guarda dietro":
                result.setSubject("Guardi dietro e vedi la " + ColorText.setTextOrange("taverna") + "da dove sei arrivato");
                break;
            case "guarda destra":
                result.setSubject("Guardi a destra e vedi il sentiero per le "+ ColorText.setTextOrange("fucine") + " e per il " + ColorText.setTextOrange("generatore"));
                break;
            case "guarda sinistra":
                result.setSubject("Guardi a sinistra e vedi il sentiero per la "+ColorText.setTextOrange("pista")+" dei kart");
                break;
            case "guarda sopra":
                result.setSubject("Vedi la parete dell'enorme caverna, è da tempo che non vedi il sole...");
                break;
            case "guarda giu":
                result.setSubject("Vedi il pavimento, è sporco di olio e grasso");
                break;

            case "vai bar":
            case "vai fucine":
            case "vai circuito":
            case "vai generatore":
                result.setResultType(RoomInteractionResultType.MOVE);
                result.setSubject(command.getCommand().getName().split(" ")[1]);
                break;

            case "vai palazzo":

                if (Inventory.contains("auto")) {
                    result.setResultType(RoomInteractionResultType.MOVE);
                    result.setSubject("palazzo");
                } else {
                    result.setResultType(RoomInteractionResultType.DESCRIPTION);
                    result.setSubject(ColorText.setTextRed("Il palazzo è chiuso, forse dovresti cercare qualcosa per sfondare il cancello"));
                }
            break;
            default:
                result.setResultType(RoomInteractionResultType.NOTHING);
        }

        return result;
    }
}
