package org.uniba.kobold.entities.inventory.availableItems;

import org.uniba.kobold.entities.inventory.Item;

import java.util.Set;

public class SteeringWheel extends Item {
    public SteeringWheel() {
        super(
                "volante",
                Set.of("volante","manubrio","manubrio d'oro"),
                "Il volante della tua auto, è plastificato e lucido",
                "/img/items/steeringwheel.jpg"
        );
    }
}
