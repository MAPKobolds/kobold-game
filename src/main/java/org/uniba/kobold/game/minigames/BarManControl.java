package org.uniba.kobold.game.minigames;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.availableItems.Beers;
import org.uniba.kobold.entities.inventory.availableItems.GinMoncello;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import org.uniba.kobold.util.ColorText;

import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

public class BarManControl extends MiniGame{

    private int state = 0;
    ArrayList<String> options = new ArrayList<>();
    ArrayList<String> market = new ArrayList<>();

    public BarManControl() {

        options.add(ColorText.setTextBlue("1)") + " [" + ColorText.setTextBlue("compra") +"]" + "<"+ColorText.setTextBlue("oggetto")+"> qualcosa dal barman");
        options.add(ColorText.setTextBlue("2)") + " Chiedi al barman cosa consiglia");
        options.add(ColorText.setTextBlue("3)") + " Chiedi al barman come funziona qui sotto");
        options.add(ColorText.setTextBlue("Esci"));

        market.add(ColorText.setTextGreen("GinMoncello") + " [500]");
        market.add("pacco da 2^4 " + ColorText.setTextGreen("birre") + " [50]");
        market.add(ColorText.setTextBlue("Esci"));

        this.description = "Benvenuto al bar, cosa vuoi fare?"+ "<br>" + String.join("<br>", options);
        this.commands.add(new Command("1", Set.of("uno")));
        this.commands.add(new Command("2", Set.of("due")));
        this.commands.add(new Command("3", Set.of("tre")));
        this.commands.add(new Command("4", Set.of("exit", "e")));
        this.commands.add(new Command("compra ginmoncello", Set.of("compra ginmoncello","ginmoncello")));
        this.commands.add(new Command("compra birre", Set.of("compra birre", "birre")));
        this.commands.add(new Command("esci", Set.of("esci")));
    }

    @Override
    public MiniGameInteraction play(ParserOutput output, Inventory inventory) {

        MiniGameInteraction interaction = new MiniGameInteraction(
                "Cosa vuoi fare?",
                null,
                MiniGameInteractionType.INFO
        );

        switch (state) {
            case 0:
                switch (output.getCommand().getName()) {

                    case "1" -> {
                        interaction.setInfo("<br>"+String.join(("<br>"), market));
                        interaction.setType(MiniGameInteractionType.INFO);
                        state = 1;
                    }
                    case "2" -> {
                        interaction.setInfo("Il barman ti dice che il suo liquore migliore è il " + ColorText.setTextGreen("GinMoncello"));
                        interaction.setType(MiniGameInteractionType.INFO);
                    }

                    case "3" -> {
                        interaction.setInfo("Il barman ti dice che a cerignolus i clienti sono sempre i benvenuti , qui si gioca a carte , si ruba e si beve non ci piace chi non rispetta le regole");
                        interaction.setType(MiniGameInteractionType.INFO);
                    }

                    case "esci" -> {
                        interaction.setInfo("Arrivederci");
                        interaction.setType(MiniGameInteractionType.EXIT);
                    }
                }
                break;

            case 1:
                switch (output.getCommand().getName()) {
                    case "compra ginmoncello" -> {
                        interaction.setInfo("Hai comprato il GinMoncello");
                        if (inventory.getMoney() >= 500) {
                            if (inventory.contains("GinMoncello")) {
                                interaction.setInfo("Hai già comprato il GinMoncello");
                                interaction.setType(MiniGameInteractionType.INFO);
                            } else {
                                inventory.removeMoney(500);
                                interaction.setResult(new GinMoncello());
                                interaction.setType(MiniGameInteractionType.WIN);
                            }
                        }else{
                            interaction.setInfo("Non hai abbastanza soldi");
                            interaction.setType(MiniGameInteractionType.INFO);
                        }
                    }
                    case "compra birre" -> {
                        if (inventory.getMoney() >= 50) {
                            if (inventory.contains("birra")) {
                                interaction.setInfo("Hai già comprato il pacco da 2^4 birre");
                                interaction.setType(MiniGameInteractionType.INFO);
                            } else {
                                inventory.removeMoney(50);
                                interaction.setInfo("Hai comprato il pacco da 2^4 birre");
                                interaction.setResult(new Beers());
                                interaction.setType(MiniGameInteractionType.WIN);
                            }
                        }else{
                            interaction.setInfo("Non hai abbastanza soldi");
                            interaction.setType(MiniGameInteractionType.INFO);
                        }
                    }
                    case "esci" -> {
                        interaction.setInfo("Buona Sbronza " + "<br>" + String.join("<br>", options));
                        interaction.setType(MiniGameInteractionType.INFO);
                        state = 0;
                    }
                }
            break;
        }

        return interaction;
    }
}
