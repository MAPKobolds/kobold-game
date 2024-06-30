package org.uniba.kobold.entities.character.availableCharacters;

import org.uniba.kobold.entities.InteractionResult;
import org.uniba.kobold.entities.character.Character;

public class TwinGuards extends Character {
    public TwinGuards() {
        super("guardie");
    }

    @Override
    public InteractionResult interact(Boolean ...args) {
        if (args.length == 0 || !args[0]) {
            System.out.print("Siamo a guardia del bar, non farci perdere tempo straniero (HANNO VOTATO VANNACCI)!");

            return InteractionResult.FAILED;
        }

        System.out.print("Prego fratello può entrare nel bar!");
        return InteractionResult.SUCCESSFUL;
    }
}
