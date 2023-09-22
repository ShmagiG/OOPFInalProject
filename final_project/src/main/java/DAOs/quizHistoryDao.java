package DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface quizHistoryDao {
    public void addQuiz(String quiz_name, String username,String score) throws SQLException;
    public void updateQuiz(int quiz_id,int user_id);
    public int getQuizId(int quiz_id,int user_id);
    public int getScore(int quiz_id,int user_id) throws SQLException;
    public int getTime(int quiz_id,int user_id) throws SQLException;
    public ResultSet getStats() throws SQLException;
    public ResultSet getQuizStats(String quiz_name) throws SQLException;
    public ResultSet getQuizStatsSortByTime(String quiz_name) throws SQLException;
    public ResultSet getQuizStatsSortByScore(String quiz_name) throws SQLException;
    public ResultSet getMaxQuizScore(String quiz_name) throws SQLException;
    //Integer getMaxQuizScore() throws SQLException;
    public ResultSet getUserStatsByTime(String Username) throws SQLException;
    public ResultSet getUserStatsByScore(String Username) throws SQLException;
    public ResultSet getUserStatsDistinct(String Username) throws SQLException;
    public ResultSet getDistinctQuizCount(String Username) throws SQLException;
    public ResultSet getMaxScorePerQuiz(String Username) throws SQLException;
    public ResultSet getCreatedByUser(String Username) throws SQLException;

    public int getQuizzesCount() throws SQLException;
    public int getQuizzesCountByUser(String Username) throws SQLException;
    public int getQuizzesCountByQuiz(String Username) throws SQLException;


}