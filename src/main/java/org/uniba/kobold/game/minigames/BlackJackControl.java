package org.uniba.kobold.game.minigames;

import org.javatuples.Pair;
import org.uniba.kobold.api.blackjack.BlackjackChecker;
import org.uniba.kobold.api.blackjack.Card;
import org.uniba.kobold.api.error.*;
import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.availableItems.Bill;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;

import java.util.List;
import java.util.Set;

public class BlackJackControl extends MiniGame{


    BlackjackChecker blackjackChecker;
    Boolean isPlaying = false;
    Boolean hasBet = false;
    Bill bettedItem = null;

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
        System.out.println("Playing BlackJack");
        MiniGameInteraction interaction = new MiniGameInteraction(
                "bambasitos non capisco cosa vuoi fare",
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
                            isPlaying = true;
                            if (blackjackChecker.getHandValue(blackjackChecker.getPlayerHand()) == 21) {
                                interaction = new MiniGameInteraction(
                                        "Hai vinto",
                                        true,
                                        new Pair<>(bettedItem, blackjackChecker.getHands()),
                                        MiniGameInteractionType.WIN
                                );
                            }else{
                                interaction = new MiniGameInteraction(
                                        "Hai iniziato una mano",
                                        false,
                                        blackjackChecker.getHands(),
                                        MiniGameInteractionType.INFO
                                );
                            }
                        } else {
                            interaction = new MiniGameInteraction(
                                    "Devi prima puntare qualcosa",
                                    false,
                                    blackjackChecker.getHands(),
                                    MiniGameInteractionType.INFO
                            );
                        }
                    } else {
                        interaction = new MiniGameInteraction(
                                "Hai già iniziato una mano",
                                false,
                                null,
                                MiniGameInteractionType.INFO
                        );
                    }
                    break;

                case "hit":
                    if (isPlaying) {
                        blackjackChecker.hit();
                        if (blackjackChecker.getHandValue(blackjackChecker.getPlayerHand()) > 21) {
                            interaction = new MiniGameInteraction(
                                    "Hai perso",
                                    true,
                                    new Pair<>(bettedItem, blackjackChecker.getHands()),
                                    MiniGameInteractionType.LOSE
                            );
                        }else{
                            interaction = new MiniGameInteraction(
                                    "Hai chiesto una carta",
                                    false,
                                    blackjackChecker.getHands(),
                                    MiniGameInteractionType.INFO
                            );
                        }
                    } else {
                        interaction = new MiniGameInteraction(
                                "Devi iniziare una mano prima",
                                false,
                                null,
                                MiniGameInteractionType.INFO
                        );
                    }
                    break;

                case "stand":
                    if (isPlaying) {
                        blackjackChecker.dealerTurn();
                        if (blackjackChecker.isBust(blackjackChecker.getDealerHand()) ||
                                blackjackChecker.isWinner(blackjackChecker.getPlayerHand(), blackjackChecker.getDealerHand())) {
                            interaction = new MiniGameInteraction(
                                    "Hai vinto",
                                    false,
                                    new Pair<>(bettedItem, blackjackChecker.getHands()),
                                    MiniGameInteractionType.WIN);
                        } else {
                            interaction = new MiniGameInteraction(
                                    "Hai perso",
                                    false,
                                    new Pair<>(bettedItem, blackjackChecker.getHands()),
                                    MiniGameInteractionType.LOSE);
                        }
                        isPlaying = false;
                    } else {
                        interaction = new MiniGameInteraction(
                                "Devi iniziare una mano prima",
                                false,
                                null,
                                MiniGameInteractionType.INFO);
                    }
                    break;

                case "bet 50":
                case "bet 100":
                case "bet 200":
                case "bet 500":
                    if (isPlaying) {
                        interaction = new MiniGameInteraction(
                                "Devi chiedere una mano prima di scommettere",
                                false,
                                null,
                                MiniGameInteractionType.INFO);
                    } else {
                        if (!hasBet) {
                            int value = Integer.parseInt(output.getCommand().getName().split(" ")[1]);
                            if (Inventory.findCurrency(value)) {
                                switch (value) {
                                    case 500:
                                    case 200:
                                    case 100:
                                    case 50:
                                        hasBet = true;
                                        bettedItem = (Bill) output.getItem();
                                        interaction = new MiniGameInteraction(
                                                "Hai scommesso " + value + " euro",
                                                false,
                                                null,
                                                MiniGameInteractionType.INFO);
                                        break;
                                    default:
                                        interaction = new MiniGameInteraction(
                                                "Devi scommettere 500, 200, 100 o 50",
                                                false,
                                                null,
                                                MiniGameInteractionType.INFO);
                                }
                            } else {
                                interaction = new MiniGameInteraction(
                                        "Non hai banconote da " + value + " euro",
                                        false,
                                        null,
                                        MiniGameInteractionType.INFO);
                            }
                        } else {
                            interaction = new MiniGameInteraction(
                                    "Hai già scommesso",
                                    false,
                                    null,
                                    MiniGameInteractionType.INFO);
                        }
                    }
                    break;

                case "exit":
                    if (isPlaying) {
                        interaction = new MiniGameInteraction(
                                "Devi terminare la mano prima di uscire",
                                false,
                                null,
                                MiniGameInteractionType.INFO);
                    } else {
                        interaction = new MiniGameInteraction(
                                "Hai terminato il gioco",
                                true,
                                null,
                                MiniGameInteractionType.INFO);
                    }
                    break;

                default:
                    interaction = new MiniGameInteraction(
                            "bambasitos non capisco cosa vuoi fare",
                            false,
                            null,
                            MiniGameInteractionType.INFO);
            }
        }
        catch (HttpInternalServerErrorException | HttpNotFoundException | HttpUnavailableException | HttpBadRequestException | HttpForbiddenException e) {
            e.printStackTrace();
        }
        return interaction;
    }
}
