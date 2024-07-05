package org.uniba.kobold.game.minigames;

import org.javatuples.Pair;
import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.entities.inventory.availableItems.Bill;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import org.uniba.kobold.util.ColorText;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SeekerGameControl extends MiniGame {
    private int currentItemIndex = 0;
    private int itemsToFindCount;
    private List<Pair<Boolean, Item>> itemList = new ArrayList<>();

    public SeekerGameControl() {
        List<Pair<Boolean, Item>> items = List.of(
            Pair.with(true, new Item("Chiave", Set.of("chiave"),"Una chiave di ferro", "/img/BR.png")),
            Pair.with(false, new Item("Pietra", Set.of("pietra"),"Una pietra", "/img/BR.png")),
            Pair.with(false, new Item("Bottiglia", Set.of("bottiglia"),"Una bottiglia di vetro", "/img/BR.png")),
            Pair.with(false, new Item("Ciondolo", Set.of("ciondolo"),"Un ciondolo d'oro", "/img/BR.png")),
            Pair.with(false, new Item("Anello", Set.of("anello"),"Un anello di diamanti", "/img/BR.png")),
            Pair.with(false, new Item("Orologio", Set.of("orologio"),"Un orologio da polso", "/img/BR.png")),
            Pair.with(false, new Item("Portafoglio", Set.of("portafoglio"),"Un portafoglio di pelle", "/img/BR.png")),
            Pair.with(false, new Item("Occhiali", Set.of("occhiali"),"Un paio di occhiali da sole", "/img/BR.png")),
            Pair.with(false, new Item("Cappello", Set.of("cappello"),"Un cappello di lana", "/img/BR.png")),
            Pair.with(false, new Item("Sciarpa", Set.of("sciarpa"),"Una sciarpa di seta", "/img/BR.png"))
        );

        itemsToFindCount = (int) items.stream().filter(object -> object.getValue0()).count();

        if(itemsToFindCount == 0 || itemsToFindCount == items.size()) {
            throw new Error("There must be at least 2 items: one to find and a fake one");
        }

        itemList.addAll(items);
        description = "Cerca i tuoi pezzi in mezzo alla spazzatura \n" +
            "ecco la lista dei comandi disponibili: \n" +
            "cerca destra/sinistra -> per andare avanti al prossimo pezzo \n" +
            "guarda -> guarda l'oggetto corrente \n" +
            "prendi -> per prendere il pezzo giusto \n";

        commands.add(new Command("cerca destra", Set.of("sposta", "vai")));
        commands.add(new Command("cerca sinistra", Set.of("sposta", "vai")));
        commands.add(new Command("prendi", Set.of("seleziona")));
        commands.add(new Command("guarda", Set.of("vedi")));
        commands.add(new Command("esci", Set.of("via")));
    }

    public SeekerGameControl(List<Pair<Boolean, Item>> items) {
        itemsToFindCount = (int) items.stream().filter(object -> object.getValue0()).count();

        if(itemsToFindCount == 0 || itemsToFindCount == items.size()) {
            throw new Error("There must be at least 2 items: one to find and a fake one");
        }

        itemList.addAll(items);
        description = "Cerca i tuoi pezzi in mezzo alla spazzatura \n" +
                "ecco la lista dei comandi disponibili: \n" +
                ColorText.setTextBlue( "cerca <destra/sinistra>") + " -> per andare avanti al prossimo pezzo \n" +
                ColorText.setTextBlue("guarda") + " -> guarda l'oggetto corrente \n" +
                ColorText.setTextBlue("prendi") + " -> per prendere il pezzo giusto \n";

        commands.add(new Command("cerca destra", Set.of("sposta", "vai")));
        commands.add(new Command("cerca sinistra", Set.of("sposta", "vai")));
        commands.add(new Command("prendi", Set.of("seleziona")));
        commands.add(new Command("guarda", Set.of("vedi")));
        commands.add(new Command("esci", Set.of("via")));
    }

    private void seekTo(Boolean direction) {
        currentItemIndex = currentItemIndex + (direction ? 1 : -1);

        if(currentItemIndex >= itemList.size()) {
            currentItemIndex = 0;
        } else if (currentItemIndex <= 0) {
            currentItemIndex = itemList.size() - 1;
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
        itemsToFindCount--;
    }

    private boolean isGameFinished() {
        return itemsToFindCount == 0;
    }

    private MiniGameInteraction pickItemInteraction() {
        MiniGameInteraction interaction = new MiniGameInteraction("",null, MiniGameInteractionType.INFO);

        try {
            this.pickItem();

            if (this.isGameFinished()) {
                interaction.setInfo("L'hai preso! Ora hai collezzionato tutti i pezzi!!");
                interaction.setType(MiniGameInteractionType.WIN_AND_EXIT);

                //TODO: REPLACE WITH CAR'S PART
                interaction.setResult(new Bill(20));
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
            null,
            MiniGameInteractionType.INFO
        );

        switch (output.getCommand().getName()) {
            case "cerca destra":
                seekTo(true);
                interaction.setInfo("Hai cercato a destra, hai trovato " + getCurrentItem().getValue1().getDescription());
                break;
            case "cerca sinistra":
                seekTo(false);
                interaction.setInfo("Hai cercato a sinistra hai trovato " + getCurrentItem().getValue1().getDescription());
                break;
            case "guarda":
                interaction.setInfo(getCurrentItem().getValue1().getDescription());
                break;
            case "prendi":
                interaction = this.pickItemInteraction();
                break;
            case "esci":
                interaction.setType(MiniGameInteractionType.EXIT);
                break;
        }

        return interaction;
    }
}
