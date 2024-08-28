package org.uniba.kobold.api.blackjack;

import org.javatuples.Pair;
import org.uniba.kobold.api.error.*;
import org.uniba.kobold.util.ColorText;

import java.util.List;

/**
 * The type Blackjack checker.
 */
public class BlackjackChecker {

    /**
     * The Player hand.
     */
    List<Card> playerHand;
    /**
     * The Dealer hand.
     */
    List<Card> dealerHand;
    /**
     * The Blackjack service.
     */
    static BlackjackService blackjackService;

    /**
     * Instantiates a new Blackjack checker.
     *
     * @throws HttpInternalServerErrorException the http internal server error exception
     * @throws HttpNotFoundException            the http not found exception
     * @throws HttpUnavailableException         the http unavailable exception
     * @throws HttpBadRequestException          the http bad request exception
     * @throws HttpForbiddenException           the http forbidden exception
     */
    public BlackjackChecker() throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        blackjackService = new BlackjackService();
        blackjackService.getDeck();
    }

    /**
     * Play round.
     *
     * @throws HttpInternalServerErrorException the http internal server error exception
     * @throws HttpNotFoundException            the http not found exception
     * @throws HttpUnavailableException         the http unavailable exception
     * @throws HttpBadRequestException          the http bad request exception
     * @throws HttpForbiddenException           the http forbidden exception
     */
    public void playRound() throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        BlackjackService blackjackService = new BlackjackService();

        playerHand = blackjackService.getCards(2);
        dealerHand = blackjackService.getCards(1);
    }

    /**
     * Gets dealer hand.
     *
     * @return the dealer hand
     */
    public List<Card> getDealerHand() {
        return dealerHand;
    }

    /**
     * Gets player hand.
     *
     * @return the player hand
     */
    public List<Card> getPlayerHand() {
        return playerHand;
    }

    /**
     * Hit.
     *
     * @throws HttpInternalServerErrorException the http internal server error exception
     * @throws HttpNotFoundException            the http not found exception
     * @throws HttpUnavailableException         the http unavailable exception
     * @throws HttpBadRequestException          the http bad request exception
     * @throws HttpForbiddenException           the http forbidden exception
     */
    public void hit() throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        playerHand.addAll(blackjackService.getCards(1));
    }

    /**
     * Dealer turn.
     *
     * @throws HttpInternalServerErrorException the http internal server error exception
     * @throws HttpNotFoundException            the http not found exception
     * @throws HttpUnavailableException         the http unavailable exception
     * @throws HttpBadRequestException          the http bad request exception
     * @throws HttpForbiddenException           the http forbidden exception
     */
    public void dealerTurn() throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        while (getHandValue(dealerHand) < 17) {
            dealerHand.addAll(blackjackService.getCards(1));
        }
    }

    /**
     * Gets hand value.
     *
     * @param dealerHand the dealer hand
     * @return the hand value
     */
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

    /**
     * Is bust boolean.
     *
     * @param hand the hand
     * @return the boolean
     */
    public boolean isBust(List<Card> hand) {
        // check if a hand is bust
        return getHandValue(hand) > 21;
    }

    /**
     * Is blackjack boolean.
     *
     * @param hand the hand
     * @return the boolean
     */
    public boolean isBlackjack(List<Card> hand) {
        // check if a hand is blackjack
        return getHandValue(hand) == 21 && hand.size() == 2;
    }

    /**
     * Is winner boolean.
     *
     * @param playerHand the player hand
     * @param dealerHand the dealer hand
     * @return the boolean
     */
    public boolean isWinner(List<Card> playerHand, List<Card> dealerHand) {
        // check if the player is the winner
        return getHandValue(playerHand) > getHandValue(dealerHand) && !isBust(playerHand);
    }

    /**
     * Get hands pair.
     *
     * @return the pair
     */
    public Pair<List<Card>,List<Card>> getHands(){
        return Pair.with(playerHand,dealerHand);
    }

    /**
     * Pretty print player hand string.
     *
     * @param isDealer the is dealer
     * @return the string
     */
    public String prettyPrintPlayerHand(boolean isDealer){
        String hand = "";
        for (Card card : isDealer ? dealerHand : playerHand) {
            switch (card.getValue()) {
                case 1 -> hand += "A ";
                case 11 -> hand += "J ";
                case 12 -> hand += "Q ";
                case 13 -> hand += "K ";
                default -> hand += card.getValue() + " ";
            }
            switch (card.getSuit()) {
                case "HEARTS" -> hand += ColorText.setTextRed("♥ ");
                case "DIAMONDS" -> hand += ColorText.setTextBlue("♦ ");
                case "CLUBS" -> hand += ColorText.setTextGreen("♣ ");
                case "SPADES" -> hand += ColorText.setTextYellow("♠ ");
            }
        }
        return hand;
    }

}
