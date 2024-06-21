package org.example.kobold.trivia;
import java.util.List;

class Quiz {
    private int responseCode;
    private List<Result> results;

    public int getResponseCode() {
        return responseCode;
    }

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