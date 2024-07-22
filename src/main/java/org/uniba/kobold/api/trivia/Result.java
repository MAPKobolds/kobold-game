package org.uniba.kobold.api.trivia;

import java.util.List;

/**
 * The type Result.
 */
class Result {
    private String type;
    private String difficulty;
    private String category;
    private String question;
    private String correct_answer;
    private List<String> incorrect_answers;

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Format string.
     *
     * @param toFormat the to format
     * @return the string
     */
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

    /**
     * Gets difficulty.
     *
     * @return the difficulty
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets question.
     *
     * @return the question
     */
    public String getQuestion() {
        return format(question);
    }

    /**
     * Gets correct answer.
     *
     * @return the correct answer
     */
    public String getCorrectAnswer() {
        return format(correct_answer);
    }

    /**
     * Gets incorrect answers.
     *
     * @return the incorrect answers
     */
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

