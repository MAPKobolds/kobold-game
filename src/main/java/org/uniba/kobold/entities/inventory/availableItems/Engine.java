package org.uniba.kobold.entities.inventory.availableItems;

import org.uniba.kobold.entities.inventory.Item;

import java.util.Set;

/**
 * The type Engine.
 */
public class Engine extends Item {
    /**
     * Instantiates a new Engine.
     */
    public Engine() {
        super(
                "motore",
                Set.of("motore"),
                "Il motore della tua auto, è un po' modificato ma va bene lo stesso",
                "/img/items/engine.jpg"
        );
    }
}
