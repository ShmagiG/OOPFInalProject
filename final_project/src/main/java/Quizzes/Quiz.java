package Quizzes;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    public static final String SINGLE_PAGE = "singlePage";
    public static final String MULTIPLE_PAGE = "multiplePage";

    private ArrayList<Problem> quiz;

    private String Name;
    private String Description;
    private String QuizType;

    public Quiz(){
        quiz = new ArrayList<>();
    }

    public void addProblem(Problem problem){
        quiz.add(problem);
    }
    public void addProblem(Question q, Answer a){
        Problem p = new Problem(q, a);
        quiz.add(p);
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }
    public void setQuizType(String quizType) {
        QuizType = quizType;
    }
    public String getName() {
        return Name;
    }

    public String getQuizType() {
        return QuizType;
    }

    public String getDescription() {
        return Description;
    }

    public ArrayList<Problem> getQuiz(){
        return quiz;
    }
}
