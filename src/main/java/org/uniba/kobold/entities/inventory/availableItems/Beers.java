package org.uniba.kobold.entities.inventory.availableItems;

import org.uniba.kobold.entities.InteractionResult;
import org.uniba.kobold.entities.inventory.InteractiveItem;
import org.uniba.kobold.entities.inventory.Item;

import java.util.Set;

public class Beers extends Item implements InteractiveItem {

    public Beers() {
        super(
                "birre", Set.of("birra"),
                "Troppe birre forse è meglio non berle tutte",
                "/img/BR.png");
    }

    @Override
    public InteractionResult interact() {
        System.out.println("Hai bevuto la birra");
        return InteractionResult.SUCCESSFUL;
    }

}
