package org.uniba.kobold.entities.inventory.availableItems;

import org.uniba.kobold.entities.inventory.Item;

import java.util.Set;

public class Engine extends Item {
    public Engine() {
        super(
                "motore",
                Set.of("motore"),
                "Il motore della tua auto, è un po' modificato ma va bene lo stesso",
                "/img/items/engine.jpg"
        );
    }
}
