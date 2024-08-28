package org.uniba.kobold.entities.inventory.availableItems;

import org.uniba.kobold.entities.inventory.Item;

import java.util.Set;

/**
 * The type Fire maul.
 */
public class FireMaul extends Item {
    /**
     * Instantiates a new Fire maul.
     */
    public FireMaul() {
        super(
                "maglio", Set.of("maglio di fuoco", "maglio", "magli"),
                "Un maglio di fuoco che brucia tutto ciò che tocca",
                "/img/items/firemaul.jpg");
    }
}
