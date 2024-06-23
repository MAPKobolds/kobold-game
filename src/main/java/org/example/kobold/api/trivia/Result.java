package org.example.kobold.api.trivia;

import java.util.List;

class Result {
    private String type;
    private String difficulty;
    private String category;
    private String question;
    private String correct_answer;
    private List<String> incorrect_answers;

    public String getType() {
        return type;
    }

    public String format (String toFormat){
        return toFormat.replace("&quot;", "\"")
                .replace("&amp;", "&")
                .replace("&apos;", "'")
                .replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&#039;", "'")
                .replace("&eacute;", "é")
                .replace("&egrave;", "è");
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getCategory() {
        return category;
    }

    public String getQuestion() {
        return format(question);
    }

    public String getCorrectAnswer() {
        return format(correct_answer);
    }

    public List<String> getIncorrect_answers() {
        List<String> incorrect_answers = this.incorrect_answers;
        incorrect_answers.replaceAll(this::format);
        return incorrect_answers;
    }

    @Override
    public String toString() {
        return "Result{" +
                "type='" + type + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", category='" + category + '\'' +
                ", question='" + question + '\'' +
                ", correctAnswer='" + correct_answer + '\'' +
                ", incorrectAnswers=" + incorrect_answers +
                '}';
    }

}

