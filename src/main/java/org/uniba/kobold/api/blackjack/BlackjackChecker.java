package org.uniba.kobold.api.blackjack;

import org.uniba.kobold.api.error.*;

import java.util.List;

public class BlackjackChecker {

    List<Card> playerHand;
    List<Card> dealerHand;
    static BlackjackService blackjackService;

    public BlackjackChecker() throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        blackjackService = new BlackjackService();
        blackjackService.getDeck();
    }

    public void playRound() throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        BlackjackService blackjackService = new BlackjackService();

        playerHand = blackjackService.getCards(2);
        dealerHand = blackjackService.getCards(2);
    }

    public List<Card> getDealerHand() {
        return dealerHand;
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public void hit() throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        playerHand.addAll(blackjackService.getCards(1));
    }

    public void dealerTurn() throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        while (getHandValue(dealerHand) < 17) {
            dealerHand.addAll(blackjackService.getCards(1));
        }
    }

    public int getHandValue(List<Card> dealerHand) {
        // get the value of a hand of cards
        int value = 0;
        int aces = 0;
        for (Card card : dealerHand) {
            if (card.getValue() == 1) {
                aces++;
            }
            value += card.getValue();
        }
        while (value < 12 && aces > 0) {
            value += 10;
            aces--;
        }
        return value;
    }

    public boolean isBust(List<Card> hand) {
        // check if a hand is bust
        return getHandValue(hand) > 21;
    }

    public boolean isBlackjack(List<Card> hand) {
        // check if a hand is blackjack
        return getHandValue(hand) == 21 && hand.size() == 2;
    }

    public boolean isWinner(List<Card> playerHand, List<Card> dealerHand) {
        // check if the player is the winner
        return getHandValue(playerHand) > getHandValue(dealerHand) && !isBust(playerHand);
    }

}
