package org.uniba.kobold.entities.character.availableCharacters;

import org.uniba.kobold.entities.InteractionResult;
import org.uniba.kobold.entities.character.Character;
import org.uniba.kobold.util.ColorText;

public class TwinGuards extends Character {
    public TwinGuards() {
        super("guardie");
    }

    @Override
    public InteractionResult interact(Boolean ...args) {
        if (args.length == 0 || !args[0]) {
            System.out.print("Siamo a guardia del bar, non farci perdere tempo straniero (HANNO VOTATO VANNACCI)!\n");

            return InteractionResult.FAILED;
        }

        System.out.print("Prego fratello può entrare nella "+ ColorText.setTextPurple("taverna") +"!\n");
        return InteractionResult.SUCCESSFUL;
    }
}
