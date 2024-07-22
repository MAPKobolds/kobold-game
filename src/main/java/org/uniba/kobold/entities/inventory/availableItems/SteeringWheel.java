package org.uniba.kobold.entities.inventory.availableItems;

import org.uniba.kobold.entities.inventory.Item;

import java.util.Set;

/**
 * The type Steering wheel.
 */
public class SteeringWheel extends Item {
    /**
     * Instantiates a new Steering wheel.
     */
    public SteeringWheel() {
        super(
                "volante",
                Set.of("volante","manubrio","manubrio d'oro"),
                "Il volante della tua auto, è plastificato e lucido",
                "/img/items/steeringwheel.jpg"
        );
    }
}
