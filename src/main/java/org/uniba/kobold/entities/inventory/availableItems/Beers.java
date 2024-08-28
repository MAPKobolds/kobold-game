package org.uniba.kobold.entities.inventory.availableItems;

import org.uniba.kobold.entities.InteractionResult;
import org.uniba.kobold.entities.inventory.InteractiveItem;
import org.uniba.kobold.entities.inventory.Item;

import java.util.Set;

/**
 * The type Beers.
 */
public class Beers extends Item implements InteractiveItem {

    /**
     * Instantiates a new Beers.
     */
    public Beers() {
        super(
                "birre", Set.of("birra"),
                "Troppe birre forse è meglio non berle tutte",
                "/img/items/beers.jpg");
    }

    @Override
    public InteractionResult interact() {
        return InteractionResult.SUCCESSFUL;
    }

}
