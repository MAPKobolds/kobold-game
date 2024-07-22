package org.uniba.kobold.entities.inventory.availableItems;

import org.uniba.kobold.entities.inventory.Item;

import java.util.Set;

/**
 * The type Gin moncello.
 */
public class GinMoncello extends Item {
    /**
     * Instantiates a new Gin moncello.
     */
    public GinMoncello() {
        super(
                "GinMoncello", Set.of("ginmoncello"),
                "Un liquore a base di gin e limoncello",
                "/img/items/ginmoncello.jpg");
    }
}
