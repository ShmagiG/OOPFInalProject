package Quizzes;

import java.util.ArrayList;
import java.util.List;

public class multipleChoiceAnswer implements Answer{
    public static final String createAnswerHtmlCode =   "<label>Enter Answers:   </label><br>" +
                                                        "<input type=\"text\" id=\"Answer1\" name=\"Answer1\"><br>" +
                                                        "<input type=\"text\" id=\"Answer2\" name=\"Answer2\"><br>" +
                                                        "<input type=\"text\" id=\"Answer3\" name=\"Answer3\"><br>" +
                                                        "<input type=\"text\" id=\"Answer4\" name=\"Answer4\"><br>" +
                                                        "<input type=\"text\" id=\"Answer5\" name=\"Answer5\"><br>" +
                                                        "<input type=\"text\" id=\"Answer6\" name=\"Answer6\"><br><br>" +
                                                        "<label>Enter Correct Answer:   </label><br>" +
                                                        "<input type=\"text\" id=\"Answer1\" name=\"CorrectAnswer\"><br>";

    private ArrayList<String> answers;
    private String correctAnswer;
    public multipleChoiceAnswer(){
        answers = new ArrayList<>();
    }
    public void addAnswer(String newAnswer){
        answers.add(newAnswer);
    }
    public void setCorrectAnswer(String a){
        correctAnswer = a;
    }
    @Override
    public String createAnswerHtmlCode() {
        return createAnswerHtmlCode;
    }

    @Override
    public String answerPromptHtmlCode(int idx) {
        String htmlString = "";// "<form>";
        for(int i = 0; i < answers.size(); i++){
            htmlString += "<input type=\"radio\" id=\"" + i + "\" name=\"userAnswer" + idx + "\" value=\"" + answers.get(i) + "\">" +
                          "<label for=\"" + i + "\">" + answers.get(i) + "</label><br>";
        }
//        htmlString += "</form>";
        return htmlString;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    @Override
    public String getAnswer() {
        return correctAnswer;
    }
}
