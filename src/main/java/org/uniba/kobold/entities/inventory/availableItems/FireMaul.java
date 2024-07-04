package org.uniba.kobold.entities.inventory.availableItems;

import org.uniba.kobold.entities.inventory.Item;

import java.util.Set;

public class FireMaul extends Item {
    public FireMaul() {
        super("maglio", Set.of("maglio di fuoco"), "Un maglio di fuoco che brucia tutto ciò che tocca", "/img/BR.png");
    }
}
