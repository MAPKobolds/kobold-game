package org.uniba.kobold.game.minigames;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import org.uniba.kobold.util.ColorText;

import java.util.Set;

/**
 * The type Twin guards control.
 */
public class TwinGuardsControl extends MiniGame{

    /**
     * Instantiates a new Twin guards control.
     */
    public TwinGuardsControl() {
        this.description = "I due guardiani ti guardano e ti chiedono cosa vuoi fare :" +
                ColorText.setTextBlue("<br>1)") + " Passare" +
                ColorText.setTextBlue("<br>2)") + " Giocare con loro";
        this.commands.add(new Command("1", Set.of("Passare")));
        this.commands.add(new Command("2", Set.of("Andare via")));
    }
    @Override
    public MiniGameInteraction play(ParserOutput output, Inventory inventory) {
        MiniGameInteraction interaction = new MiniGameInteraction(
                "I due guardiani ti guardano male e ti respingono dicendo che non puoi passare, devi trovare un modo per passare per non farti scoprire. Ti consigliano di andare alla " + ColorText.setTextPurple("caverna"),
                null,
                MiniGameInteractionType.EXIT
        );

        switch (output.getCommand().getName() ) {
            case "1":
                if (inventory.contains("mantello")){
                    interaction.setInfo("I due guardiani ti guardano e ti fanno passare, non sospetta nulla, adesso puoi accedere alla " + ColorText.setTextPurple("taverna"));
                    interaction.setResult("taverna");
                    interaction.setType(MiniGameInteractionType.UNLOCK);
                }
            break;
        }

        return interaction;
    }
}
