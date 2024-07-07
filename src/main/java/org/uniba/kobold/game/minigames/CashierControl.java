package org.uniba.kobold.game.minigames;

import org.javatuples.Pair;
import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.availableItems.SteeringWheel;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import org.uniba.kobold.util.ColorText;

import java.util.Set;

public class CashierControl extends MiniGame{

    public CashierControl() {
        this.description = "Amico mio, la prima puntata costa molto cara, \n" +
                "se perdi sei fregato, per puntare scrivi su questo foglio il numero o il nome del pilota vincente\n" +
                ColorText.setTextBlue("1) Hamilton\n") +
                ColorText.setTextBlue("2) Verstappen\n") +
                ColorText.setTextBlue("3) Vettel\n") +
                ColorText.setTextBlue("4) Leclerc\n") +
                ColorText.setTextBlue("Esci\n");

        this.commands.add(new Command("1", Set.of("Hamilton")));
        this.commands.add(new Command("2", Set.of("Verstappen")));
        this.commands.add(new Command("3", Set.of("Vettel")));
        this.commands.add(new Command("4", Set.of("Leclerc")));
        this.commands.add(new Command("esci", Set.of("esci")));



    }
    @Override
    public MiniGameInteraction play(ParserOutput output, Inventory inventory) {
        MiniGameInteraction interaction = new MiniGameInteraction(
                "Due buttafuori coboldi si avvicinano a te e ti portano a lavorare per 3 anni nelle fucine per ripagare il debito perchè :\n",
                null,
                MiniGameInteractionType.EXIT

        );

        switch (output.getCommand().getName() ) {
            case "1":
                interaction.addInfo("Hai scommesso su Hamilton ma ha vinto Verstappen, hai perso tutto!");
                break;
            case "2":
                interaction.setInfo("Hai scommesso su Verstappen hai vinto un magnifico premio!" +
                        "\n" + ColorText.setTextGreen("Hai vinto un manubrio d'oro per la tua auto!"));
                interaction.setResult(new SteeringWheel());
                interaction.setType(MiniGameInteractionType.WIN);
                break;
            case "3":
                interaction.addInfo("Hai scommesso su Vettel ma ha vinto Leclerc, hai perso tutto!");
                break;
            case "4":
                interaction.addInfo("Hai scommesso su Leclerc ma ha vinto Hamilton, hai perso tutto!");
                break;
            case "esci":
                interaction.setInfo("Hai deciso di non scommettere, però senti che c'è qualcosa di strano...");
                interaction.setType(MiniGameInteractionType.EXIT);
                break;
        }
        return interaction;
    }
}
