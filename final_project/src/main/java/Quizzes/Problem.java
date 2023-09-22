package Quizzes;

public class Problem {
    private Question question;
    private Answer answer;

    public Problem(){}

    public Problem(Question question, Answer answer){
        this.question = question;
        this.answer = answer;
    }

    public void setQuestion(Question question){
        this.question = question;
    }



    public void setAnswer(Answer answer){
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public Answer getAnswer() {
        return answer;
    }
}
