package org.uniba.kobold.api.trivia;
import org.uniba.kobold.api.error.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/**
 * The type Trivia checker.
 */
public class TriviaChecker {
    /**
     * The Quiz.
     */
    Quiz quiz;
    /**
     * The Is correct.
     */
    int[] isCorrect;

    /**
     * Instantiates a new Trivia checker.
     *
     * @throws HttpInternalServerErrorException the http internal server error exception
     * @throws HttpNotFoundException            the http not found exception
     * @throws HttpUnavailableException         the http unavailable exception
     * @throws HttpBadRequestException          the http bad request exception
     * @throws HttpForbiddenException           the http forbidden exception
     */
    public TriviaChecker() throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {

        quiz = TriviaService.getTrivia();
        isCorrect = new int[quiz.getResults().size()];
    }

    /**
     * Get questions string [ ].
     *
     * @return the string [ ]
     */
    public String[] getQuestions() {
        String[] questions = new String[quiz.getResults().size()];
        for (Result result : quiz.getResults()) {
            questions[quiz.getResults().indexOf(result)] = result.getQuestion();
        }
        return questions;
    }

    /**
     * Gets answers shuffled.
     *
     * @return the answers shuffled
     */
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

    /**
     * Is correct boolean.
     *
     * @param questionIndex the question index
     * @param answerIndex   the answer index
     * @return the boolean
     */
    public Boolean isCorrect(int questionIndex,int answerIndex) {
        return isCorrect[questionIndex] == answerIndex;
    }
}