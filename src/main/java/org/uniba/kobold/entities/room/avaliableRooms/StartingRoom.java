package org.uniba.kobold.entities.room.avaliableRooms;

import org.uniba.kobold.entities.inventory.availableItems.Cloak;
import org.uniba.kobold.entities.room.Room;
import org.uniba.kobold.entities.room.RoomInteractionResult;
import org.uniba.kobold.entities.room.RoomInteractionResultType;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import java.util.Arrays;
import java.util.Set;

public final class StartingRoom extends Room {

    public StartingRoom() {
        super("caverna",
            "Ti svegli in una caverna buia, non ricordi come ci sei arrivato, ma senti un forte dolore alla testa.\n" +
                    "Guardandoti intorno è buio pesto, senti qualcosa di appiccicoso sotto di te.\n",
            "/img/BR.png",
            Arrays.asList(new Cloak()),
            Arrays.asList(),
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
            case "guarda giù":
                result.setSubject("Guardi giù e vedi un qualcosa di appiccicoso sotto di te di colore verde scuro, che esce da un tessuto nero, sembra un mantello nero.");
                break;
            case "guarda davanti":
                result.setSubject("Guardi davanti un corridoio buio");
                break;
            case "guarda dietro":
                result.setSubject("Guardi dietro e vedi una parete");
                break;
            case "guarda sopra":
                result.setSubject("Guardi sopra e vedi il buco da cui sei caduto");
                break;
            case "guarda destra":
                result.setSubject("Guardi a destra e vedi una parete");
                break;
            case "guarda sinistra":
                result.setSubject("Guardi a sinistra e vedi una parete");
                break;
            case "prendi":
                if (command.getItem() != null && command.getItem().getName().equals("mantello")) {
                    result.setResultType(RoomInteractionResultType.ADD_ITEM);
                    result.setSubject("Hai preso il mantello");
                    result.setArgument(command.getItem());
                } else {
                    result.setSubject("Cosa vuoi prendere?");
                }
                break;
            case "ispeziona":
                if (command.getItem().getName().equals("mantello")) {
                    result.setResultType(RoomInteractionResultType.ADD_ITEM);
                    result.setSubject(command.getItem().getDescription() + "\nHai indossato il mantello, ora sei irriconoscibile");
                } else {
                    result.setSubject("Non c'è niente da ispezionare");
                }
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
