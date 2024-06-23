package org.uniba.kobold.api.blackjack;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import com.google.gson.reflect.TypeToken;
import org.uniba.kobold.api.error.HttpRequestHandler;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * A class that represents the interface to the deck of cards API
 */
public class BlackjackService {

    /**
     * Attributes of the BlackjackService class
     */
    private String deckToken;
    private int remainingCards;
    private static final int LIMIT = 156;

    /**
     * Constructor
     */
    public BlackjackService() {
        getDeck();
    }

    /**
     * Make a GET request to a URL and return the response body
     * @param url the URL to make the request to
     * @return the response body
     */
    private static String getRequest(String url){
        // Create a new client and initiate a request
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        String responseBody;

        // verify the response status code and handle it accordingly
        // if the status code is not 200, throw a runtime exception
        // otherwise, return the response body
        try {
            Response resp = target.request(MediaType.APPLICATION_JSON).get();
            responseBody = resp.readEntity(String.class);
            int status = resp.getStatus();
            HttpRequestHandler.handle(status);
        }catch (Exception e){
            throw new RuntimeException("Failed to make request to " + url, e);
        }

        return responseBody;
    }

    /**
     * Deserialize a JSON response from a URL to a map
     * @param url the URL to get the JSON response from
     * @return a map of the JSON response
     */
    public Map<String,Object> deserializeFromUrl(String url){
        // get the response body from the url and cast it to a map
        String responseBody = getRequest(url);
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        return gson.fromJson(responseBody, type);
    }

    /**
     * Get a new deck of cards
     */
    public void getDeck(){

        // get a new deck of cards and set the deck token and remaining cards
        String url = "https://www.deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1";
        Map<String,Object> response = deserializeFromUrl(url);
        deckToken = response.get("deck_id").toString();
        String remaining = response.get("remaining").toString();
        remainingCards = Integer.parseInt(remaining.substring(0, remaining.length() - 2));
    }

    /**
     * Get a number of cards from the deck
     * @param numberOfCards the number of cards to get
     * @return a list of Card objects
     */
    public List<Card> getCards(int numberOfCards){

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
     */
    public void shuffleDeck(){
        // shuffle the deck of cards
        String url = "https://www.deckofcardsapi.com/api/deck/" + deckToken + "/shuffle/";
        getRequest(url);
    }

}
