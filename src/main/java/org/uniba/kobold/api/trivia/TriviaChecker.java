package org.uniba.kobold.api.trivia;
import org.uniba.kobold.api.error.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class TriviaChecker {
    Quiz quiz;
    int[] isCorrect;

    public TriviaChecker() throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {

        quiz = TriviaService.getTrivia();
        isCorrect = new int[quiz.getResults().size()];
    }

    public String[] getQuestions() {
        String[] questions = new String[quiz.getResults().size()];
        for (Result result : quiz.getResults()) {
            questions[quiz.getResults().indexOf(result)] = result.getQuestion();
        }
        return questions;
    }

    public List<List<String>> getAnswersShuffled() {
        List<List<String>> answers = new ArrayList<>();
        for( Result result : quiz.getResults()) {
            List<String> answersList = new ArrayList<>();
            answersList.add(result.getCorrectAnswer());
            answersList.addAll(result.getIncorrect_answers());
            Collections.shuffle(answersList);
            for(String answer : answersList) {
                if(answer.equals(result.getCorrectAnswer())) {
                    isCorrect[quiz.getResults().indexOf(result)] = answersList.indexOf(answer);
                }
            }
            answers.add(answersList);
        }

        return answers;
    }

    public Boolean isCorrect(int questionIndex,int answerIndex) {
        return isCorrect[questionIndex] == answerIndex;
    }
}