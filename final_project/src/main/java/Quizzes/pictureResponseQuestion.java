package Quizzes;

public class pictureResponseQuestion implements Question{
    private String imgUrl;

    public static final String QUESTION_NAME = "pictureResponseQuestion";
    public static final String createQuestionHtmlCode =     "<label for=\"" + QUESTION_NAME + "\">Enter image address:   </label>" +
                                                            "<input type=\"text\" id=\"" + QUESTION_NAME + "\" name=\"" + QUESTION_NAME + "\"><br>";
    public static final String NAME = "Picture-Response";

    public pictureResponseQuestion(String url){
        imgUrl = url;
    }
    @Override
    public String createQuestionHtmlCode() {
        return createQuestionHtmlCode;
    }

    @Override
    public String questionHtmlCode() {
        return "<br><img src=\"" + imgUrl + "\" width=\"100\" height=\"100\"><br>";
    }

    @Override
    public String getQuestionType() {
        return NAME;
    }

    public String getImgUrl(){
        return imgUrl;
    }
}

