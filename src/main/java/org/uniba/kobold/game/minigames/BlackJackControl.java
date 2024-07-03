package org.uniba.kobold.game.minigames;

import org.uniba.kobold.api.blackjack.BlackjackChecker;
import org.uniba.kobold.api.error.*;
import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;

import java.util.Set;

public class BlackJackControl extends MiniGame{
    BlackjackChecker blackjackChecker;
    Boolean isPlaying = false;
    Boolean hasBet = false;
    int bettedMoney = 0;

    public BlackJackControl() throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        blackjackChecker = new BlackjackChecker();

        description =
                "Benvento al BlackJack \n" +
                        "ecco la lista dei comandi disponibili: \n" +
                        "play/p -> per iniziare una nuova mano \n" +
                        "hit/h -> per chiedere una carta \n" +
                        "stand/s -> per fermarsi\n" +
                        "come puoi vedere noi coboldi siamo molto bravi a giocare a Blackjack e qui non si gioca con numeri ma con pile di banconote!\n" +
                        "bet/b <500/200/100/50>-> per scommettere\n" +
                        "exit/e -> per uscire dal gioco";

        commands.add(new Command("hit",Set.of("hit","h")));
        commands.add(new Command("stand",Set.of("stand","s")));
        commands.add(new Command("bet 50",Set.of("50")));
        commands.add(new Command("bet 100",Set.of("100")));
        commands.add(new Command("bet 200",Set.of("200")));
        commands.add(new Command("bet 500",Set.of("500")));
        commands.add(new Command("exit",Set.of("exit","e")));
        commands.add(new Command("play",Set.of("play","p")));

    }

    @Override
    public MiniGameInteraction play(ParserOutput output) {

        MiniGameInteraction interaction = new MiniGameInteraction(
                "Stanno succedendo cose strane",
                false,
                null,
                MiniGameInteractionType.INFO
        );

        try {
            switch (output.getCommand().getName()) {
                case "play":
                    if (!isPlaying) {
                        if (hasBet) {
                            blackjackChecker.playRound();
                            if (blackjackChecker.getHandValue(blackjackChecker.getPlayerHand()) == 21) {
                                hasBet = false;
                                Inventory.addMoney(bettedMoney);

                                interaction = new MiniGameInteraction(
                                        "Hai vinto",
                                        true,
                                        blackjackChecker.getHands(),
                                        MiniGameInteractionType.WIN
                                );

                            } else {
                                isPlaying = true;

                                interaction.setResult(blackjackChecker.getHands());
                                interaction.setInfo("Hai iniziato una mano");
                            }
                        } else {
                            interaction.setResult(blackjackChecker.getHands());
                            interaction.setInfo("Devi prima puntare qualcosa");
                        }
                    } else {
                        interaction.setInfo("Hai già iniziato una mano");
                    }
                    break;

                case "hit":
                    if (isPlaying) {
                        blackjackChecker.hit();
                        if (blackjackChecker.getHandValue(blackjackChecker.getPlayerHand()) > 21) {
                            Inventory.removeMoney(bettedMoney);

                            isPlaying = false;
                            hasBet = false;

                            interaction = new MiniGameInteraction(
                                    "Hai perso",
                                    true,
                                    blackjackChecker.getHands(),
                                    MiniGameInteractionType.LOSE
                            );

                        } else {
                            interaction.setInfo("Hai chiesto una carta");
                            interaction.setResult(blackjackChecker.getHands());
                        }
                    } else {
                        interaction.setInfo("Devi iniziare una mano prima");
                    }
                    break;

                case "stand":
                    if (isPlaying) {

                        hasBet = false;
                        isPlaying = false;
                        blackjackChecker.dealerTurn();
                        boolean hasWon = blackjackChecker.isBust(blackjackChecker.getDealerHand()) ||
                                         blackjackChecker.isWinner(blackjackChecker.getPlayerHand(), blackjackChecker.getDealerHand());

                        if (hasWon) {
                            Inventory.addMoney(bettedMoney);
                        } else {
                            Inventory.removeMoney(bettedMoney);
                        }

                        interaction.setInfo(hasWon ? "Hai vinto" : "Hai perso");
                        interaction.setResult(blackjackChecker.getHands());
                        interaction.setType(hasWon ? MiniGameInteractionType.WIN : MiniGameInteractionType.LOSE);
                    } else {
                        interaction.setInfo("Devi iniziare una mano prima");
                    }
                    break;

                case "bet 50" :
                case "bet 100" :
                case "bet 200" :
                case "bet 500" :
                    if (isPlaying) {
                        interaction.setInfo("Devi terminare la mano prima di scommettere");
                    } else {
                        if (!hasBet) {
                            int value = Integer.parseInt(output.getCommand().getName().split(" ")[1]);

                            if (Inventory.getMoney() >= value) {
                                hasBet = true;
                                bettedMoney = value;
                                interaction.setInfo("Hai scommesso " + value + " euro");

                            } else {
                                interaction.setInfo("Non hai abbastanza soldi");
                            }
                        } else {
                            interaction.setInfo("Hai già scommesso");
                        }
                    }
                    break;

                case "exit":
                    if (isPlaying) {
                        interaction.setInfo("Devi terminare la mano prima di uscire");
                    } else {
                        interaction.setInfo("Hai abbandonato il tavolo");
                    }
                break;
            }
        }
        catch (HttpInternalServerErrorException | HttpNotFoundException | HttpUnavailableException | HttpBadRequestException | HttpForbiddenException e) {
            e.printStackTrace();
        }
        return interaction;
    }
}
