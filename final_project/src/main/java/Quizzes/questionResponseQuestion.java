package Quizzes;

public class questionResponseQuestion  implements Question{
    private String question;

    public static final String NAME = "Question-Response";
    public static final String QUESTION_NAME = "questionResponseQuestion";

    public static final String createQuestionHtmlCode =     "<label for=\"" + QUESTION_NAME + "\">Enter Question:   </label>" +
                                                            "<input type=\"text\" id=\"" + QUESTION_NAME + "\" name=\"" + QUESTION_NAME + "\"><br>";



    public questionResponseQuestion(String question){
        this.question = question;
    }

    @Override
    public String createQuestionHtmlCode() {
        return createQuestionHtmlCode;
    }

    @Override
    public String questionHtmlCode() {
        return "<p>" + question + "</p>";
    }
    @Override
    public String getQuestionType() {
        return NAME;
    }

    public String getQuestion(){
        return question;
    }
}
