package org.uniba.kobold.api.trivia;
import java.util.List;

/**
 * The type Quiz.
 */
class Quiz {
    private int responseCode;
    private List<Result> results;

    /**
     * Gets response code.
     *
     * @return the response code
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * Gets results.
     *
     * @return the results
     */
    public List<Result> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "responseCode=" + responseCode +
                ", results=" + results +
                '}';
    }
}