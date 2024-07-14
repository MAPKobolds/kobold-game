package org.uniba.kobold.game.minigames;

import org.uniba.kobold.api.blackjack.BlackjackChecker;
import org.uniba.kobold.api.error.*;
import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import org.uniba.kobold.util.ColorText;

import java.awt.*;
import java.util.Set;

public class BlackJackControl extends MiniGame{
    BlackjackChecker blackjackChecker;
    Boolean isPlaying = false;
    Boolean hasBet = false;
    String commandsHelp = "";
    int scommettitedMoney = 0;

    public BlackJackControl() throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        blackjackChecker = new BlackjackChecker();

        commandsHelp = "ecco la lista dei comandi disponibili: <br>" +
                ColorText.setTextBlue("gioca/g") + " -> per iniziare una nuova mano <br>" +
                ColorText.setTextBlue("carta/c") + " -> per chiedere una carta <br>" +
                ColorText.setTextBlue("stai/s") + " -> per fermarsi<br>" +
                ColorText.setTextBlue("scommetti") +" " + ColorText.setTextPurple("[500/200/100/50]") + "-> per scommettere<br>" +
                ColorText.setTextBlue("aiuto/a") +" -> per visualizzare i comandi disponibili<br>" +
                ColorText.setTextBlue("esci/e") +" -> per uscire dal gioco";

        description =
                ColorText.setTextPurple("[Benvento al BlackJack] <br>") +
                        "Il Coboldo Crupier ti da il benvenuto al tavolo del BlackJack<br>" + commandsHelp;

        commands.add(new Command("carta",Set.of("carta","c")));
        commands.add(new Command("stai",Set.of("stai","s")));
        commands.add(new Command("scommetti 50",Set.of("50")));
        commands.add(new Command("scommetti 100",Set.of("100")));
        commands.add(new Command("scommetti 200",Set.of("200")));
        commands.add(new Command("scommetti 500",Set.of("500")));
        commands.add(new Command("esci",Set.of("esci","e")));
        commands.add(new Command("gioca",Set.of("gioca","g")));
        commands.add(new Command("aiuto",Set.of("aiuto","a")));

    }

    @Override
    public MiniGameInteraction play(ParserOutput output, Inventory inventory) {

        MiniGameInteraction interaction = new MiniGameInteraction(
                "Stanno succedendo cose strane",
                null,
                MiniGameInteractionType.INFO
        );

        try {
            switch (output.getCommand().getName()) {
                case "gioca":
                    if (!isPlaying) {
                        if (hasBet) {
                            blackjackChecker.playRound();
                            if (blackjackChecker.getHandValue(blackjackChecker.getPlayerHand()) == 21) {
                                hasBet = false;
                                inventory.addMoney(scommettitedMoney);

                                interaction = new MiniGameInteraction(
                                        "il dealer ha: " + blackjackChecker.prettyPrintPlayerHand(true) + "<br>" + "Tu hai: " +
                                        blackjackChecker.prettyPrintPlayerHand(false) + "<br>" +ColorText.setTextGreen("Hai vinto " + scommettitedMoney + " euro"),
                                        blackjackChecker.getHands(),
                                        MiniGameInteractionType.INFO
                                );

                            } else {
                                isPlaying = true;

                                interaction.setResult(blackjackChecker.getHands());
                                interaction.setInfo("Hai iniziato una mano le tue carte sono: " + blackjackChecker.prettyPrintPlayerHand(false) + "<br>" + "Il dealer ha: " + blackjackChecker.prettyPrintPlayerHand(true) + "<br>" + "Cosa vuoi fare?");
                            }
                        } else {
                            interaction.setResult(blackjackChecker.getHands());
                            interaction.setInfo(ColorText.setTextRed("Devi prima puntare qualcosa"));
                        }
                    } else {
                        interaction.setInfo(ColorText.setTextRed("Hai già iniziato una mano"));
                    }
                    break;

                case "carta":
                    if (isPlaying) {
                        blackjackChecker.hit();
                        if (blackjackChecker.getHandValue(blackjackChecker.getPlayerHand()) > 21) {
                            inventory.removeMoney(scommettitedMoney);

                            isPlaying = false;
                            hasBet = false;

                            interaction = new MiniGameInteraction(
                                    blackjackChecker.prettyPrintPlayerHand(false) + "<br>" +
                                    ColorText.setTextRed("Hai perso " + scommettitedMoney + " euro"),
                                    blackjackChecker.getHands(),
                                    MiniGameInteractionType.INFO
                            );

                        } else {
                            interaction.setInfo("Hai chiesto una carta" + "<br>" + blackjackChecker.prettyPrintPlayerHand(false) +  "<br>" + "Il dealer ha: " + blackjackChecker.prettyPrintPlayerHand(true));
                            interaction.setResult(blackjackChecker.getHands());
                        }
                    } else {
                        interaction.setInfo(ColorText.setTextRed("Devi iniziare una mano prima"));
                    }
                    break;

                case "stai":
                    if (isPlaying) {

                        hasBet = false;
                        isPlaying = false;
                        blackjackChecker.dealerTurn();
                        boolean hasWon = blackjackChecker.isBust(blackjackChecker.getDealerHand()) ||
                                         blackjackChecker.isWinner(blackjackChecker.getPlayerHand(), blackjackChecker.getDealerHand());

                        if (hasWon) {
                            inventory.addMoney(scommettitedMoney);
                        } else {
                            inventory.removeMoney(scommettitedMoney);
                        }

                        String finalResult = "Il dealer ha: " + blackjackChecker.prettyPrintPlayerHand(true) + "<br>" +
                                "Tu hai: " + blackjackChecker.prettyPrintPlayerHand(false) + "<br>" +
                                (hasWon ? ColorText.setTextGreen("Hai vinto ")   : ColorText.setTextRed("Hai perso ")) + scommettitedMoney + " euro";
                        interaction.setInfo(finalResult);
                        interaction.setResult(blackjackChecker.getHands());
                        
                    } else {
                        interaction.setInfo(ColorText.setTextRed("Devi iniziare una mano prima"));
                    }
                    break;

                case "scommetti 50" :
                case "scommetti 100" :
                case "scommetti 200" :
                case "scommetti 500" :
                    if (isPlaying) {
                        interaction.setInfo(ColorText.setTextRed("Devi terminare la mano prima di scommettere"));
                    } else {
                        if (!hasBet) {
                            int value = Integer.parseInt(output.getCommand().getName().split(" ")[1]);

                            if (inventory.getMoney() >= value) {
                                hasBet = true;
                                scommettitedMoney = value;
                                interaction.setInfo("Hai scommesso " + ColorText.setTextPurple(String.valueOf(value)) + " euro");

                            } else {
                                interaction.setInfo(ColorText.setTextRed("Non hai abbastanza soldi"));
                            }
                        } else {
                            interaction.setInfo(ColorText.setTextRed("Hai già scommesso"));
                        }
                    }
                    break;

                case "esci":
                    if (isPlaying || hasBet) {
                        interaction.setInfo(ColorText.setTextRed("Devi terminare la mano prima di uscire"));
                    } else {
                        interaction.setInfo("Hai abbandonato il tavolo");
                        interaction.setResult(MiniGameInteractionType.EXIT);
                    }
                break;

                case "aiuto":
                    interaction.setInfo(commandsHelp);
                    break;
            }
        }
        
        catch (HttpInternalServerErrorException | HttpNotFoundException | HttpUnavailableException | HttpBadRequestException | HttpForbiddenException e) {
            e.printStackTrace();
        }
        return interaction;
    }
}
