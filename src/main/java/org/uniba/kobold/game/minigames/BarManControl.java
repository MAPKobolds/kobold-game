package org.uniba.kobold.game.minigames;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.availableItems.Birra;
import org.uniba.kobold.entities.inventory.availableItems.GinMoncello;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;

import java.util.ArrayList;
import java.util.Set;

public class BarManControl extends MiniGame{

    private int state = 0;
    ArrayList<String> options = new ArrayList<>();
    ArrayList<String> market = new ArrayList<>();

    public BarManControl() {

        options.add("1) Compra qualcosa dal barman");
        options.add("2) Chiedi al barman cosa consiglia");
        options.add("3) Chiedi al barman come funziona qui sotto");
        options.add("4) Esci");

        market.add("1) GinMoncello [500]");
        market.add("2) pacco da 2^4 birre [50]");
        market.add("3) Esci");

        this.description = "Benvenuto al bar, cosa vuoi fare?"+ "\n" + String.join("\n", options);
        this.commands.add(new Command("1", Set.of("uno")));
        this.commands.add(new Command("2", Set.of("due")));
        this.commands.add(new Command("3", Set.of("tre")));
        this.commands.add(new Command("4", Set.of("exit", "e")));

    }

    @Override
    public MiniGameInteraction play(ParserOutput output) {

        MiniGameInteraction interaction = new MiniGameInteraction(
                "Cosa vuoi fare?",
                null,
                MiniGameInteractionType.INFO
        );

        switch (state) {
            case 0:
                switch (output.getCommand().getName()) {

                    case "1" -> {
                        interaction.setInfo("\n"+String.join(("\n"), market));
                        interaction.setType(MiniGameInteractionType.INFO);
                        state = 1;
                    }
                    case "2" -> {
                        interaction.setInfo("Il barman ti dice che il suo liquore migliore è il GinMoncello");
                        interaction.setType(MiniGameInteractionType.INFO);
                    }

                    case "3" -> {
                        interaction.setInfo("Il barman ti dice che a cerignolus i clienti sono sempre i benvenuti , qui si gioca a carte , si ruba e si beve non ci piace chi non rispetta le regole");
                        interaction.setType(MiniGameInteractionType.INFO);
                    }

                    case "4" -> {
                        interaction.setInfo("Arrivederci");
                        interaction.setType(MiniGameInteractionType.INFO);
                    }
                }
                break;

            case 1:
                switch (output.getCommand().getName()) {
                    case "1" -> {
                        interaction.setInfo("Hai comprato il GinMoncello");
                        if (Inventory.getMoney() >= 500) {
                            if (Inventory.contains("GinMoncello")) {
                                interaction.setInfo("Hai già comprato il GinMoncello");
                                interaction.setType(MiniGameInteractionType.INFO);
                            } else {
                                Inventory.removeMoney(500);
                                Inventory.addPiece(new GinMoncello());
                                interaction.setType(MiniGameInteractionType.INFO);
                            }
                        }else{
                            interaction.setInfo("Non hai abbastanza soldi");
                            interaction.setType(MiniGameInteractionType.INFO);
                        }
                    }
                    case "2" -> {
                        if (Inventory.getMoney() >= 50) {
                            if (Inventory.contains("birra")) {
                                interaction.setInfo("Hai già comprato il pacco da 2^4 birre");
                                interaction.setType(MiniGameInteractionType.INFO);
                            } else {
                                Inventory.removeMoney(50);
                                interaction.setInfo("Hai comprato il pacco da 2^4 birre");
                                Inventory.addPiece(new Birra());
                                interaction.setType(MiniGameInteractionType.INFO);
                            }
                        }else{
                            interaction.setInfo("Non hai abbastanza soldi");
                            interaction.setType(MiniGameInteractionType.INFO);
                        }
                    }
                    case "3" -> {
                        interaction.setInfo("Buona Sbronza " + "\n" + String.join("\n", options));
                        interaction.setType(MiniGameInteractionType.INFO);
                        state = 0;
                    }
                }
            break;
        }

        return interaction;
    }
}
