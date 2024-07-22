package org.uniba.kobold.entities.inventory.availableItems;

import org.uniba.kobold.entities.inventory.Item;
import java.util.Set;

/**
 * The type Car.
 */
public class Car extends Item {

    /**
     * Instantiates a new Car.
     */
    public Car() {
        super(
            "auto",
            Set.of(""),
            "La tua auto forse è un po modificata, ma va bene lo stesso",
            "/img/items/car.jpg"
        );
    }
}
