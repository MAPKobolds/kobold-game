package org.uniba.kobold.entities.inventory.availableItems;

import org.uniba.kobold.entities.inventory.Item;
import java.util.Set;

public class Car extends Item {

    public Car() {
        super(
            "auto",
            Set.of(""),
            "La tua auto forse è un po modificata, ma va bene lo stesso",
            "/img/BR.png"
        );
    }
}
