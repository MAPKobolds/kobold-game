package org.uniba.kobold.entities.inventory.availableItems;
import org.uniba.kobold.entities.inventory.Item;

import java.util.Set;

public class Cloak extends Item {
    public Cloak() {
        super(
                "mantello", Set.of("mantello", "cappa", "mantellaccio" , "un mantello nero" , "/img/BR.png"),
                "Un mantello nero usurato,ti va stretto,\nma è sufficente a coprire quasi tutto il tuo corpo.",
                "/img/BR.png");
    }

}
