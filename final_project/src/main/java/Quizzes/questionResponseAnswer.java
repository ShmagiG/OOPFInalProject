package Quizzes;

public class questionResponseAnswer implements  Answer{
    private String answer;
    public static final String ANSWER_NAME = "questionResponseAnswer";
    public static final String createAnswerHtmlCode =   "<label for=\"" + ANSWER_NAME + "\">Enter Answer:   </label>" +
                                                        "<input type=\"text\" id=\"" + ANSWER_NAME + "\" name=\"" + ANSWER_NAME + "\"><br>";

    public questionResponseAnswer(String answer){
        this.answer = answer;
    }

    @Override
    public String createAnswerHtmlCode() {
        return createAnswerHtmlCode;
    }

    @Override
    public String answerPromptHtmlCode(int idx) {
        return  "<label>Enter answer:</label>" +
                "<input type=\"text\" name=\"userAnswer" + idx + "\"><br>";
    }
    @Override
    public String getAnswer() {
        return answer;
    }
}
