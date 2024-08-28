package org.uniba.kobold.entities.inventory.availableItems;

import org.uniba.kobold.entities.inventory.Item;

import java.util.Set;

/**
 * The type Car body.
 */
public class CarBody extends Item {

    /**
     * Instantiates a new Car body.
     */
    public CarBody() {
        super(
                "carrozzeria",
                Set.of("carrozzeria"),
                "La carrozzeria della tua auto, è rossa e lucida",
                "/img/items/carbody.jpg"
        );
    }
}
