package Quizzes;

public class fillBlankQuestion implements Question{
    private String firstPart;
    private String secondPart;

    public static final String NAME = "Fill in the Blank";
    public static final String FIRST_PART_NAME = "firstPart";
    public static final String SECOND_PART_NAME = "secondPart";

    public static final String createQuestionHtmlCode = "<label for=\"" + FIRST_PART_NAME + "\">Enter first part:   </label>" +
                                                        "<input type=\"text\" id=\"" + FIRST_PART_NAME + "\" name=\"" + FIRST_PART_NAME + "\"><br>" +
                                                        "<label for=\"" + SECOND_PART_NAME + "\">Enter second part:   </label>" +
                                                        "<input type=\"text\" id=\"" + SECOND_PART_NAME + "\" name=\"" + SECOND_PART_NAME + "\"><br>";



    public fillBlankQuestion(String s1, String s2){
        firstPart = s1;
        secondPart = s2;
    }

    @Override
    public String createQuestionHtmlCode() {
        return createQuestionHtmlCode;
    }

    @Override
    public String questionHtmlCode() {
        return "<p>" + firstPart + "_______" + secondPart + "</p>";
    }
    @Override
    public String getQuestionType() {
        return NAME;
    }

    public String getFirstPart() {
        return firstPart;
    }

    public String getSecondPart() {
        return secondPart;
    }
}
