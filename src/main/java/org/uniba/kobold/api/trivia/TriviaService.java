package org.uniba.kobold.api.trivia;
import com.google.gson.Gson;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.uniba.kobold.api.error.*;

class TriviaService {

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

    public static Quiz getTrivia() throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        String responseBody = getRequest("https://opentdb.com/api.php?amount=6&category=11&difficulty=easy");

        Gson gson = new Gson();

        return gson.fromJson(responseBody, Quiz.class);
    }
}
