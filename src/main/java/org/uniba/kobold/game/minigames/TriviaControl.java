package org.uniba.kobold.game.minigames;

import org.uniba.kobold.api.error.*;
import org.uniba.kobold.api.trivia.TriviaChecker;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;

import java.util.List;
import java.util.Set;

public class TriviaControl extends MiniGame{

    TriviaChecker triviaChecker;
    String[] questions;
    List<List<String>> answers;
    private int score = 0;
    private int round = 0;

    public TriviaControl() throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        this.commands.add(new Command("1", Set.of("uno")));
        this.commands.add(new Command("2", Set.of("due")));
        this.commands.add(new Command("3", Set.of("tre")));
        this.commands.add(new Command("4", Set.of("quattro")));
        this.commands.add(new Command("vero", Set.of("v")));
        this.commands.add(new Command("falso", Set.of("f")));
        this.commands.add(new Command("esci", Set.of("e")));
        triviaChecker = new TriviaChecker();
        questions = triviaChecker.getQuestions();
        answers = triviaChecker.getAnswersShuffled();

        this.description = "Rispondi a 3 domande su 6 correttamente per vincere il gioco"+ "\n" ;
        this.description += questions[0];
        for (int i = 0; i < answers.getFirst().size(); i++) {
            this.description += "\n" + (i + 1) + ") " + answers.getFirst().get(i);
        }
    }

    @Override
    public MiniGameInteraction play(ParserOutput output) {
        MiniGameInteraction result = new MiniGameInteraction("dummy", null , MiniGameInteractionType.INFO);
        int answerIndex = -1;

        switch (output.getCommand().getName()) {
            case "vero", "1" -> answerIndex = 0;
            case "falso", "2" -> answerIndex = 1;
            case "3" -> answerIndex = 2;
            case "4" -> answerIndex = 3;
            case "esci" -> {
                result.setInfo("Hai abbandonato il gioco");
                result.setType(MiniGameInteractionType.EXIT);
            }
        }

        if ( round >= 5 ){
            result.setInfo("Non abbiamo così tante domande ,_,");
            result.setType(MiniGameInteractionType.EXIT);
        } else {

            round++;

            if (triviaChecker.isCorrect(round, answerIndex)) {

                if (score == 2) {
                    result.setInfo("Hai vinto il gioco");
                    result.setType(MiniGameInteractionType.WIN);
                } else {
                    String ToSend = "Risposta corretta" + "\n" + questions[round];

                    for (int i = 0; i < answers.get(round).size(); i++) {
                        ToSend += "\n" + (i + 1) + ") " + answers.get(round).get(i);
                    }

                    result.setInfo(ToSend);

                    score++;
                }

            } else {
                String ToSend = "Risposta sbagliata" + "\n" + questions[round];

                for (int i = 0; i < answers.get(round).size(); i++) {
                    ToSend += "\n" + (i + 1) + ") " + answers.get(round).get(i);
                }

                result.setInfo(ToSend);
            }
        }

        return result;
    }
}
