package org.uniba.kobold.game.minigames;

import org.uniba.kobold.api.blackjack.BlackjackChecker;
import org.uniba.kobold.api.error.*;
import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import org.uniba.kobold.util.ColorText;

import java.util.Set;

public class BlackJackControl extends MiniGame{
    BlackjackChecker blackjackChecker;
    Boolean isPlaying = false;
    Boolean hasBet = false;
    int scommettitedMoney = 0;

    public BlackJackControl() throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        blackjackChecker = new BlackjackChecker();

        description =
                ColorText.setTextPurple("<br>[Benvento al BlackJack] <br>") +
                        "Il Coboldo Crupier ti da il benvenuto al tavolo del BlackJack<br>"+
                        "ecco la lista dei comandi disponibili: <br>" +
                        ColorText.setTextBlue("gioca/g") + " -> per iniziare una nuova mano <br>" +
                        ColorText.setTextBlue("carta/c") + " -> per chiedere una carta <br>" +
                        ColorText.setTextBlue("stai/s") + " -> per fermarsi<br>" +
                        "come puoi vedere noi coboldi siamo molto bravi a giocare a Blackjack e qui non si gioca con numeri ma con pile di banconote!<br>" +
                        ColorText.setTextBlue("scommetti") +" " + ColorText.setTextPurple("<500/200/100/50>") + "-> per scommettere<br>" +
                        ColorText.setTextBlue("esci/e") +" -> per uscire dal gioco";

        commands.add(new Command("carta",Set.of("carta","c")));
        commands.add(new Command("stai",Set.of("stai","s")));
        commands.add(new Command("scommetti 50",Set.of("50")));
        commands.add(new Command("scommetti 100",Set.of("100")));
        commands.add(new Command("scommetti 200",Set.of("200")));
        commands.add(new Command("scommetti 500",Set.of("500")));
        commands.add(new Command("esci",Set.of("esci","e")));
        commands.add(new Command("gioca",Set.of("gioca","g")));

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
                                        ColorText.setTextGreen("Hai vinto " + scommettitedMoney + " euro"),
                                        blackjackChecker.getHands(),
                                        MiniGameInteractionType.INFO
                                );

                            } else {
                                isPlaying = true;

                                interaction.setResult(blackjackChecker.getHands());
                                interaction.setInfo("Hai iniziato una mano");
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
                                    ColorText.setTextRed("Hai perso " + scommettitedMoney + " euro"),
                                    blackjackChecker.getHands(),
                                    MiniGameInteractionType.INFO
                            );

                        } else {
                            interaction.setInfo("Hai chiesto una carta");
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
                        String finalResult = (hasWon ? "Hai vinto "   : "Hai perso ") + scommettitedMoney + " euro";
                        interaction.setInfo((hasWon ? ColorText.setTextGreen(finalResult) : ColorText.setTextRed(finalResult)));
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
                    if (isPlaying) {
                        interaction.setInfo(ColorText.setTextRed("Devi terminare la mano prima di uscire"));
                    } else {
                        interaction.setInfo("Hai abbandonato il tavolo");
                        interaction.setResult(MiniGameInteractionType.EXIT);
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
