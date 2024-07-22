package org.uniba.kobold.api.blackjack;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import com.google.gson.reflect.TypeToken;
import org.uniba.kobold.api.error.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * A class that represents the interface to the deck of cards API
 */
class BlackjackService {

    /**
     * Attributes of the BlackjackService class
     */
    private String deckToken;
    private int remainingCards;
    private static final int LIMIT = 156;

    /**
     * Constructor
     *
     * @throws HttpInternalServerErrorException the http internal server error exception
     * @throws HttpNotFoundException            the http not found exception
     * @throws HttpUnavailableException         the http unavailable exception
     * @throws HttpBadRequestException          the http bad request exception
     * @throws HttpForbiddenException           the http forbidden exception
     */
    public BlackjackService() throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        getDeck();
    }

    /**
     * Make a GET request to a URL and return the response body
     * @param url the URL to make the request to
     * @return the response body
     */
    private static String getRequest(String url) throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        // Create a new client and initiate a request
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        int status;
        String responseBody;

        // verify the response status code and handle it accordingly
        // if the status code is not 200, throw a runtime exception
        // otherwise, return the response body
        try {
            Response resp = target.request(MediaType.APPLICATION_JSON).get();
            responseBody = resp.readEntity(String.class);
            status = resp.getStatus();
        }catch (Exception e){
            throw new RuntimeException("Failed to make request to " + url, e);
        }

        HttpRequestHandler.handle(status);

        return responseBody;
    }

    /**
     * Deserialize a JSON response from a URL to a map
     *
     * @param url the URL to get the JSON response from
     * @return a map of the JSON response
     * @throws HttpInternalServerErrorException the http internal server error exception
     * @throws HttpNotFoundException            the http not found exception
     * @throws HttpUnavailableException         the http unavailable exception
     * @throws HttpBadRequestException          the http bad request exception
     * @throws HttpForbiddenException           the http forbidden exception
     */
    public Map<String,Object> deserializeFromUrl(String url) throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        // get the response body from the url and cast it to a map
        String responseBody = getRequest(url);
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        return gson.fromJson(responseBody, type);
    }

    /**
     * Get a new deck of cards
     *
     * @throws HttpInternalServerErrorException the http internal server error exception
     * @throws HttpNotFoundException            the http not found exception
     * @throws HttpUnavailableException         the http unavailable exception
     * @throws HttpBadRequestException          the http bad request exception
     * @throws HttpForbiddenException           the http forbidden exception
     */
    public void getDeck() throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {

        // get a new deck of cards and set the deck token and remaining cards
        String url = "https://www.deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1";
        Map<String,Object> response = deserializeFromUrl(url);
        deckToken = response.get("deck_id").toString();
        String remaining = response.get("remaining").toString();
        remainingCards = Integer.parseInt(remaining.substring(0, remaining.length() - 2));
    }

    /**
     * Get a number of cards from the deck
     *
     * @param numberOfCards the number of cards to get
     * @return a list of Card objects
     * @throws HttpInternalServerErrorException the http internal server error exception
     * @throws HttpNotFoundException            the http not found exception
     * @throws HttpUnavailableException         the http unavailable exception
     * @throws HttpBadRequestException          the http bad request exception
     * @throws HttpForbiddenException           the http forbidden exception
     */
    public List<Card> getCards(int numberOfCards) throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {

        // if the number of remaining cards is less than the limit, shuffle the deck
        if (remainingCards < LIMIT )shuffleDeck();

        // get a number of cards from the deck and return them as a list of Card objects
        String url = "https://www.deckofcardsapi.com/api/deck/" + deckToken + "/draw/?count=" + numberOfCards;
        Type type = new TypeToken<List<Map<String, Object>>>(){}.getType();
        Gson gson = new Gson();

        Map<String,Object> response = deserializeFromUrl(url);
        String remaining = response.get("remaining").toString();
        remainingCards = Integer.parseInt(remaining.substring(0, remaining.length() - 2));

        return gson.fromJson(gson.toJson(response.get("cards")), new TypeToken<List<Card>>() {}.getType());
    }

    /**
     * Shuffle the deck of cards
     *
     * @throws HttpInternalServerErrorException the http internal server error exception
     * @throws HttpNotFoundException            the http not found exception
     * @throws HttpUnavailableException         the http unavailable exception
     * @throws HttpBadRequestException          the http bad request exception
     * @throws HttpForbiddenException           the http forbidden exception
     */
    public void shuffleDeck() throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        // shuffle the deck of cards
        String url = "https://www.deckofcardsapi.com/api/deck/" + deckToken + "/shuffle/";
        getRequest(url);
    }

}
