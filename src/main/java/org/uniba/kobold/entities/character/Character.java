package org.uniba.kobold.entities.character;

import org.uniba.kobold.entities.InteractionResult;

public abstract class Character {
    private final String name;

    public Character(String name) {
        this.name = name;
    }

    public abstract InteractionResult interact(Boolean ...args);

    public final String getName() {
        return name;
    }
}
