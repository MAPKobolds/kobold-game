package org.uniba.kobold.entities.inventory.availableItems;
import org.uniba.kobold.entities.inventory.Item;

import java.util.Set;

/**
 * The type Cloak.
 */
public class Cloak extends Item {
    /**
     * Instantiates a new Cloak.
     */
    public Cloak() {
        super(
            "mantello", Set.of("mantello", "cappa", "mantellaccio" , "un mantello nero" , "/img/BR.png"),
            "Un mantello nero usurato,ti va stretto,<br>ma è sufficente a coprire quasi tutto il tuo corpo.",
            "/img/items/cloak.jpg"
        );
    }

}
