package Quizzes;

public interface Answer {
    public String createAnswerHtmlCode();
    public String answerPromptHtmlCode(int idx);
    public String getAnswer();
}
