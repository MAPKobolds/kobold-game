package org.uniba.kobold.entities.character;

import org.uniba.kobold.entities.InteractionResult;

public abstract class Character {
    private String name;

    public Character(String name) {
        this.name = name;
    }

    public InteractionResult interact() {
        return InteractionResult.SUCCESSFUL;
    }

}
