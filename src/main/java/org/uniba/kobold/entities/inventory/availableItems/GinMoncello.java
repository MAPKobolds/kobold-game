package org.uniba.kobold.entities.inventory.availableItems;

import org.uniba.kobold.entities.inventory.Item;

import java.util.Set;

public class GinMoncello extends Item {
    public GinMoncello() {
        super(
                "GinMoncello", Set.of("ginmoncello"),
                "Un liquore a base di gin e limoncello",
                "/img/items/ginmoncello.jpg");
    }
}
