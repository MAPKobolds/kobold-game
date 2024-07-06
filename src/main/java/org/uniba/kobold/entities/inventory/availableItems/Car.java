package org.uniba.kobold.entities.inventory.availableItems;

import org.uniba.kobold.entities.inventory.ModularItem;

import java.util.List;
import java.util.Set;

public class Car extends ModularItem {

    public Car() {
        super(
                "auto",
                "La tua auto forse è un po modificata, ma va bene lo stesso",
                Set.of(""),
                "/img/BR.png",
                List.of(Engine.class, CarBody.class , SteeringWheel.class)
        );
    }
}
