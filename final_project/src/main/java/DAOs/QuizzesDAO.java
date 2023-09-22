package DAOs;

import Quizzes.*;

import java.sql.*;
import java.util.ArrayList;

public class QuizzesDAO {
    private static final String addQuizCommand = "INSERT INTO quizzes (name, description, quiz_type, num_participants_made,author) VALUES (?, ?, ?, ?, ?);";

    private static final String addQuestionResponseCommand = "INSERT INTO question_response (quiz_id, question, answer) VALUES (?, ?, ?);";
    private static final String addPictureResponseCommand = "INSERT INTO picture_response (quiz_id, url, answer) VALUES (?, ?, ?);";
    private static final String addFillBlankCommand = "INSERT INTO fill_blank (quiz_id, first_part, second_part, answer) VALUES (?, ?, ?, ?);";
    private static final String addMultipleChoiceCommand = "INSERT INTO multiple_choice (quiz_id, question, correct_answer, " +
            "answer1, answer2, answer3, answer4, answer5, answer6) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String getLastId = "SELECT max(id) FROM quizzes;";

    private static final String quizExists = "SELECT * FROM quizzes q where q.id = ";

    private static final String getAllQuizzes = "SELECT * FROM quizzes;";

    private static final String getAllQuizzesSBCDPrefix = "SELECT * FROM quizzes q "; //SBCD - sort by creation date
    private static final String getAllQuizzesSBCDSuffix = " ORDER BY q.quiz_creation_date DESC;";

    private static final String getAllQuizzesSBPPrefix = "SELECT * FROM quizzes q "; //SBP - sort by popularity
    private static final String getAllQuizzesSBPSuffix = " ORDER BY q.num_participants_made DESC;";

    private static final String getQuizzesCount = "SELECT COUNT(*) FROM quizzes;";

    private static final String searchQuizzesByName = "SELECT * FROM quizzes q WHERE q.name LIKE ";

    private static final String updateQuizPopularity = "UPDATE quizzes SET num_participants_made = num_participants_made + 1 WHERE id =";

    private final Connection conn;
    public QuizzesDAO() {
        conn = DBConnection.getConnection();
    }
    public int getMaxId() throws SQLException {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(getLastId);
        rs.next();
        int maxId = rs.getInt(1);
        return maxId;
    }
    private void addProblem(Problem p, int quizId) throws SQLException {
        PreparedStatement prepStmt;
        if(p.getQuestion().getQuestionType().equals(questionResponseQuestion.NAME)){
            prepStmt = conn.prepareStatement(addQuestionResponseCommand);
            prepStmt.setInt(1, quizId);
            prepStmt.setString(2, ((questionResponseQuestion)p.getQuestion()).getQuestion());
            prepStmt.setString(3, p.getAnswer().getAnswer());
            prepStmt.executeUpdate();
        } else if(p.getQuestion().getQuestionType().equals(pictureResponseQuestion.NAME)){
            prepStmt = conn.prepareStatement(addPictureResponseCommand);
            prepStmt.setInt(1, quizId);
            prepStmt.setString(2, ((pictureResponseQuestion)p.getQuestion()).getImgUrl());
            prepStmt.setString(3, p.getAnswer().getAnswer());
            prepStmt.executeUpdate();
        } else if(p.getQuestion().getQuestionType().equals(fillBlankQuestion.NAME)){
            prepStmt = conn.prepareStatement(addFillBlankCommand);
            prepStmt.setInt(1, quizId);
            prepStmt.setString(2, ((fillBlankQuestion)p.getQuestion()).getFirstPart());
            prepStmt.setString(3, ((fillBlankQuestion)p.getQuestion()).getSecondPart());
            prepStmt.setString(4, p.getAnswer().getAnswer());
            prepStmt.executeUpdate();
        } else if(p.getQuestion().getQuestionType().equals(multipleChoiceQuestion.NAME)){
            prepStmt = conn.prepareStatement(addMultipleChoiceCommand);
            prepStmt.setInt(1, quizId);
            prepStmt.setString(2, ((multipleChoiceQuestion)p.getQuestion()).getQuestion());
            prepStmt.setString(3, p.getAnswer().getAnswer());

            ArrayList<String> possibleAnswers = ((multipleChoiceAnswer)p.getAnswer()).getAnswers();
            for(int i = 0; i < possibleAnswers.size(); i++){
                prepStmt.setString(i + 4, possibleAnswers.get(i));
            }
            for(int i = possibleAnswers.size() + 4; i < 10; i++){
                prepStmt.setString(i, null);
            }

            prepStmt.executeUpdate();
        }
    }
    public void addQuiz(Quiz quiz, String author) throws SQLException {
        PreparedStatement prepStmt = conn.prepareStatement(addQuizCommand);
        prepStmt.setString(1, quiz.getName());
        prepStmt.setString(2, quiz.getDescription());
        prepStmt.setString(3, quiz.getQuizType());
        prepStmt.setInt(4, 0);
        prepStmt.setString(5, author);
        prepStmt.executeUpdate();

        int quizId = getMaxId();

        ArrayList<Problem> problems = quiz.getQuiz();
        for(Problem p : problems){
            addProblem(p, quizId);
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String getQuizById = "SELECT * from quizzes q where q.id = ";

    private static final String getQuestionResponseByQuizId = "SELECT * from question_response q where q.quiz_id = ";
    private static final String getPictureResponseByQuizId = "SELECT * from picture_response p where p.quiz_id = ";
    private static final String getFillBlankByQuizId = "SELECT * from fill_blank f where f.quiz_id = ";
    private static final String getMultipleChoiceByQuizId = "SELECT * from multiple_choice m where m.quiz_id = ";

    private ResultSet executeStatement(String execute, int quizId) throws SQLException {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(execute + quizId + ";");
        return rs;
    }
    private void addQuizInformation(Quiz q, int quizId) throws SQLException {
        ResultSet rs = executeStatement(getQuizById, quizId);
        if(rs.next()){
            q.setName(rs.getString(2));
            q.setDescription(rs.getString(3));
            q.setQuizType(rs.getString(4));
        }
    }
    private void addQuestionResponseProblems(Quiz q, int quizId) throws SQLException {
        ResultSet rs = executeStatement(getQuestionResponseByQuizId, quizId);
        while(rs.next()){
            Question newQuestion = new questionResponseQuestion(rs.getString(2));
            Answer newAnswer = new questionResponseAnswer(rs.getString(3));

            q.addProblem(new Problem(newQuestion, newAnswer));
        }
    }
    private void addPictureResponseProblems(Quiz q, int quizId) throws SQLException {
        ResultSet rs = executeStatement(getPictureResponseByQuizId, quizId);
        while(rs.next()){
            Question newQuestion = new pictureResponseQuestion(rs.getString(2));
            Answer newAnswer = new pictureResponseAnswer(rs.getString(3));

            q.addProblem(new Problem(newQuestion, newAnswer));
        }
    }
    private void addFillBlankProblems(Quiz q, int quizId) throws SQLException {
        ResultSet rs = executeStatement(getFillBlankByQuizId, quizId);
        while(rs.next()){
            Question newQuestion = new fillBlankQuestion(rs.getString(2), rs.getString(3));
            Answer newAnswer = new fillBlankAnswer(rs.getString(4));

            q.addProblem(new Problem(newQuestion, newAnswer));
        }
    }
    private void addMultipleChoiceProblems(Quiz q, int quizId) throws SQLException {
        ResultSet rs = executeStatement(getMultipleChoiceByQuizId, quizId);
        while(rs.next()){
            Question newQuestion = new multipleChoiceQuestion(rs.getString(2));
            Answer newAnswer = new multipleChoiceAnswer();
            ((multipleChoiceAnswer) newAnswer).setCorrectAnswer(rs.getString(3));
            for(int i = 4; i < 10; i++){
                if(rs.getString(i) == null){
                    break;
                }
                ((multipleChoiceAnswer) newAnswer).addAnswer(rs.getString(i));
            }
            q.addProblem(new Problem(newQuestion, newAnswer));
        }
    }
    public Quiz getQuiz(int quizId) throws SQLException {
        if(quizId > getMaxId()){
            return null;
        }
        Quiz q = new Quiz();

        addQuizInformation(q, quizId);
        addQuestionResponseProblems(q, quizId);
        addPictureResponseProblems(q, quizId);
        addFillBlankProblems(q, quizId);
        addMultipleChoiceProblems(q, quizId);
        return q;
    }
    public String getQuizName(int quizId) throws SQLException {
        if(quizId > getMaxId()){
            return null;
        }
        ResultSet rs = executeStatement(getQuizById, quizId);
        if(rs.next()){
            return rs.getString(2);
        }
        return null;
    }
    public String getQuizDescription(int quizId) throws SQLException {
        if(quizId > getMaxId()){
            return null;
        }
        ResultSet rs = executeStatement(getQuizById, quizId);
        if(rs.next()){
            return rs.getString(3);
        }
        return null;
    }
    public boolean quizExists(int quizId) throws SQLException {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(quizExists + quizId + ";");
        if(rs.next()){
            return true;
        }
        return false;
    }
    public ResultSet orderByPopularity(String name) throws SQLException {
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stm.executeQuery(getAllQuizzesSBPPrefix + " WHERE q.name LIKE '%" + name + "%' " + getAllQuizzesSBPSuffix);
        return rs;
    }
    public ResultSet orderByCreationDate(String name) throws SQLException {
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stm.executeQuery(getAllQuizzesSBCDPrefix + " WHERE q.name LIKE '%" + name + "%' " + getAllQuizzesSBCDSuffix);
        return rs;
    }
    public ResultSet allRows() throws SQLException {
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stm.executeQuery(getAllQuizzes);
        return rs;
    }
    public ResultSet searchByName(String name) throws SQLException {
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stm.executeQuery(searchQuizzesByName + "'%" + name + "%';");
        return rs;
    }
    public int getQuizzesCount() throws SQLException {
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stm.executeQuery(getQuizzesCount);
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }
    public void updateQuizPopularity(int quizID) throws SQLException {
        Statement stm = conn.createStatement();
        stm.executeUpdate(updateQuizPopularity + quizID + ";");
    }

    public ResultSet GetQuizzesCountByAuthor(String author) throws SQLException {
        PreparedStatement prepStmt = conn.prepareStatement(
                "select count(name) from quizzes where author = ?;"
        );

        prepStmt.setString(1, author);
        ResultSet rs = prepStmt.executeQuery();
        return rs;
    }

    public ResultSet GetQuizzesByAuthor(String author) throws SQLException {
        PreparedStatement prepStmt = conn.prepareStatement(
                "select name , quiz_creation_date from quizzes where author = ?;"
        );

        prepStmt.setString(1, author);
        ResultSet rs = prepStmt.executeQuery();
        return rs;
    }

}
