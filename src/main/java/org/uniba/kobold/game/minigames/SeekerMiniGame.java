package org.uniba.kobold.game.minigames;

import org.javatuples.Pair;
import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SeekerMiniGame extends MiniGame {
    private int currentItemIndex = 0;
    private int itemsToFindCount;
    private List<Pair<Boolean, Item>> itemList = new ArrayList<>();

    public SeekerMiniGame(List<Pair<Boolean, Item>> items) {
        itemsToFindCount = (int) items.stream().filter(object -> object.getValue0()).count();

        if(itemsToFindCount == 0 || itemsToFindCount == items.size()) {
            throw new Error("There must be at least 2 items: one to find and a fake one");
        }

        itemList.addAll(items);
        description = "Cerca i tuoi pezzi in mezzo alla spazzatura \n" +
            "ecco la lista dei comandi disponibili: \n" +
            "cerca destra/sinistra -> per andare avanti al prossimo pezzo \n" +
            "prendi -> per prendere il pezzo giusto \n";

        commands.add(new Command("cerca destra", Set.of("sposta", "vai")));
        commands.add(new Command("cerca sinistra", Set.of("sposta", "vai")));
        commands.add(new Command("prendi",Set.of("seleziona","s")));
    }

    private void seekTo(Boolean direction) {
        currentItemIndex = currentItemIndex + (direction ? 1 : -1);

        if(currentItemIndex >= itemList.size()) {
            currentItemIndex = 0;
        } else if (currentItemIndex <= 0) {
            currentItemIndex = itemList.size();
        }

    }

    public Pair<Boolean, Item> getCurrentItem() {
        return itemList.get(currentItemIndex);
    }

    private void pickItem() {
        if(!getCurrentItem().getValue0()) {
            throw new Error("You cannot pick a fake item");
        }

        itemList.remove(currentItemIndex);
        currentItemIndex = 0;
        itemsToFindCount--;;
    }

    private boolean isGameFinished() {
        return itemsToFindCount == 0;
    }

    private MiniGameInteraction pickItemInteraction() {
        MiniGameInteraction interaction = new MiniGameInteraction("", false, null, MiniGameInteractionType.INFO);

        try {
            this.pickItem();

            if (this.isGameFinished()) {
                interaction.setInfo("Hai collezzionato tutti i pezzi!!");
                interaction.setHasFinished(true);
            } else {
                interaction.setInfo("Hai trovato un pezzo, te ne mancano " + itemsToFindCount);
            }

        } catch (Error e) {
            interaction.setInfo("Forse questo non è l'oggetto che cerchi");
        }

        return interaction;
    }

    @Override
    public MiniGameInteraction play(ParserOutput output) {
        MiniGameInteraction interaction = new MiniGameInteraction(
            "Utilizza i comandi disponibili",
            false,
            null,
            MiniGameInteractionType.INFO
        );

        switch (output.getCommand().getName()) {
            case "cerca destra":
                seekTo(true);
                interaction.setInfo("Hai cercato a destra");
                break;
            case "cerca sinistra":
                seekTo(false);
                interaction.setInfo("Hai cercato a sinistra");
            case "prendi":
                interaction = this.pickItemInteraction();
        }

        return interaction;
    }
}
