package org.uniba.kobold.game.minigames;

import org.javatuples.Pair;
import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.entities.inventory.availableItems.CarBody;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import org.uniba.kobold.util.ColorText;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The type Seeker game control.
 */
public class SeekerGameControl extends MiniGame {
    private int currentItemIndex = 0;
    private int itemsToFindCount;
    private final List<Pair<Boolean, Item>> itemList = new ArrayList<>();

    /**
     * Instantiates a new Seeker game control.
     */
    public SeekerGameControl() {
        List<Pair<Boolean, Item>> items = List.of(
            Pair.with(true, new Item("Portelle", Set.of("portelle"),"delle portelle", "/img/rocks.png")),
            Pair.with(false, new Item("Bottiglia", Set.of("bottiglia"),"Una bottiglia di vetro", "/img/rocks.png")),
            Pair.with(false, new Item("Ciondolo", Set.of("ciondolo"),"Un ciondolo d'oro", "/img/rocks.png")),
            Pair.with(false, new Item("Anello", Set.of("anello"),"Un anello di diamanti", "/img/rocks.png")),
            Pair.with(false, new Item("Orologio", Set.of("orologio"),"Un orologio da polso", "/img/rocks.png")),
            Pair.with(true, new Item("Scheletro auto", Set.of("scheletro auto"),"Lo scheletro di un auto", "/img/rocks.png")),
            Pair.with(false, new Item("Occhiali", Set.of("occhiali"),"Un paio di occhiali da sole", "/img/rocks.png")),
            Pair.with(false, new Item("Cappello", Set.of("cappello"),"Un cappello di lana", "/img/rocks.png")),
            Pair.with(false, new Item("Sciarpa", Set.of("sciarpa"),"Una sciarpa di seta", "/img/rocks.png")),
            Pair.with(true, new Item("Cofano", Set.of("cofano"),"Il cofano ", "/img/rocks.png"))
        );

        itemsToFindCount = (int) items.stream().filter(object -> object.getValue0()).count();

        if(itemsToFindCount == 0 || itemsToFindCount == items.size()) {
            throw new Error("There must be at least 2 items: one to find and a fake one");
        }

        itemList.addAll(items);
        description = "Cerca i tuoi pezzi in mezzo alla spazzatura <br>" +
            "ecco la lista dei comandi disponibili: <br>" +
            ColorText.setTextBlue("cerca destra/sinistra") + " -> per andare avanti al prossimo pezzo <br>" +
            ColorText.setTextBlue("guarda") +"-> guarda l'oggetto corrente <br>" +
            ColorText.setTextBlue("prendi") +" -> per prendere il pezzo giusto <br>";

        commands.add(new Command("cerca destra", Set.of("sposta", "vai")));
        commands.add(new Command("cerca sinistra", Set.of("sposta", "vai")));
        commands.add(new Command("prendi", Set.of("seleziona")));
        commands.add(new Command("guarda", Set.of("vedi")));
        commands.add(new Command("esci", Set.of("via")));
    }

    /**
     * Instantiates a new Seeker game control.
     *
     * @param items the items
     */
    public SeekerGameControl(List<Pair<Boolean, Item>> items) {
        itemsToFindCount = (int) items.stream().filter(object -> object.getValue0()).count();

        if(itemsToFindCount == 0 || itemsToFindCount == items.size()) {
            throw new Error("There must be at least 2 items: one to find and a fake one");
        }

        itemList.addAll(items);
        description = "Cerca i tuoi pezzi in mezzo alla spazzatura <br>" +
                "ecco la lista dei comandi disponibili: <br>" +
                ColorText.setTextBlue( "cerca <destra/sinistra>") + " -> per andare avanti al prossimo pezzo <br>" +
                ColorText.setTextBlue("guarda") + " -> guarda l'oggetto corrente <br>" +
                ColorText.setTextBlue("prendi") + " -> per prendere il pezzo giusto <br>";

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

    /**
     * Gets current item.
     *
     * @return the current item
     */
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
                interaction.setInfo("L'hai preso! Ora hai collezionato tutti i pezzi!!");
                interaction.setType(MiniGameInteractionType.WIN);
                interaction.setResult(new CarBody());
            } else {
                interaction.setInfo("Hai trovato un pezzo, te ne mancano " + itemsToFindCount);
            }

        } catch (Error e) {
            interaction.setInfo("Forse questo non è l'oggetto che cerchi");
        }

        return interaction;
    }

    @Override
    public MiniGameInteraction play(ParserOutput output, Inventory inventory) {
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
