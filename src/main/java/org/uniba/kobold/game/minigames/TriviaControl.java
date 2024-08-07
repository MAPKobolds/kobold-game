package org.uniba.kobold.game.minigames;

import org.uniba.kobold.api.error.*;
import org.uniba.kobold.api.trivia.TriviaChecker;
import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.availableItems.Engine;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import org.uniba.kobold.util.ColorText;

import java.awt.*;
import java.util.List;
import java.util.Set;

/**
 * The type Trivia control.
 */
public class TriviaControl extends MiniGame{

    /**
     * The Trivia checker.
     */
    TriviaChecker triviaChecker;
    /**
     * The Questions.
     */
    String[] questions;
    /**
     * The Answers.
     */
    List<List<String>> answers;
    private int score = 0;
    private int round = 0;

    /**
     * Instantiates a new Trivia control.
     *
     * @throws HttpInternalServerErrorException the http internal server error exception
     * @throws HttpNotFoundException            the http not found exception
     * @throws HttpUnavailableException         the http unavailable exception
     * @throws HttpBadRequestException          the http bad request exception
     * @throws HttpForbiddenException           the http forbidden exception
     */
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

        this.description = ColorText.setTextBlue("Rispondi a 3 domande su 6 correttamente per vincere il gioco<br>");
        this.description += questions[0];
        for (int i = 0; i < answers.getFirst().size(); i++) {
            this.description += ColorText.setTextBlue("<br>" + (i + 1) + ") ") + answers.getFirst().get(i);
        }
    }

    @Override
    public MiniGameInteraction play(ParserOutput output, Inventory inventory) {
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
        if (answerIndex == -1) {
            result.setInfo("Non ho capito la tua risposta, riprova");
            return result;
        }else {
            if (round >= 5) {
                result.setInfo(ColorText.setTextRed("Hai perso ancora !!! ") + "<br> il direttore però apprezza l'aiuto e ti chiede di " + ColorText.setTextBlue("aiutarlo") + "ancora");
                result.setType(MiniGameInteractionType.EXIT);
            } else {
                round+=1;
                if (triviaChecker.isCorrect(round - 1, answerIndex)) {

                    if (score == 2) {
                        result.setInfo("Il direttore ti vede con ammirazione e poichè non lo pagano abbastanza ti consegna un " + ColorText.setTextGreen("motore") + " nuovo di zecca!");
                        result.setResult(new Engine());
                        result.setType(MiniGameInteractionType.WIN);
                    } else {
                        String ToSend = ColorText.setTextGreen("Risposta corretta") + "<br>" + questions[round];

                        for (int i = 0; i < answers.get(round).size(); i++) {
                            ToSend += "<br>" + ColorText.setTextBlue((i + 1) + ") ") + answers.get(round).get(i);
                        }

                        result.setInfo(ToSend);

                        score++;
                    }
                } else {
                    String ToSend = ColorText.setTextRed("Risposta sbagliata") + "<br>" + questions[round];

                    for (int i = 0; i < answers.get(round).size(); i++) {
                        ToSend += "<br>" + ColorText.setTextBlue((i + 1) + ") ") + answers.get(round).get(i);
                    }

                    result.setInfo(ToSend);
                }
            }
        }

        return result;
    }
}
