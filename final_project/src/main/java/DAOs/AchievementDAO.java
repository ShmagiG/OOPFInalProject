package DAOs;

import ObjectClasses.Achievement;
import ObjectClasses.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* am DAOstan iqneba kide samushao wesit */

public class AchievementDAO {
    private final Connection conn;

    public AchievementDAO(){
        conn = DBConnection.getConnection();
    }

    public List<Achievement> getUserAchievements(User user) throws Exception {
        List<Achievement> achievements = new ArrayList<>();

        PreparedStatement prepStmt = conn.prepareStatement("SELECT achievement_id FROM user_achievements" +
                " WHERE user_id = ?;");
        prepStmt.setInt(1, user.getUserId());
        ResultSet rs = prepStmt.executeQuery();

        while(rs.next()){
            achievements.add(getAchievementById(rs.getInt("achievement_id")));
        }

        return achievements;
    }

    public boolean addAchievementToUser(User user, Achievement achievement) throws SQLException {
        if(!userHasAchievement(user, achievement)){
            PreparedStatement prepStmt = conn.prepareStatement("INSERT INTO user_achievements (user_id, achievement_id) " +
                    " VALUES (?, ?);");
            prepStmt.setInt(1, user.getUserId());
            prepStmt.setInt(2, achievement.getId());
            prepStmt.executeUpdate();
            return true;
        }
        return false;
    }

    public boolean userHasAchievement(User user, Achievement achievement) throws SQLException {
        PreparedStatement prepStmt = conn.prepareStatement("SELECT id FROM user_achievements WHERE" +
                " user_id = ? AND achievement_id = ?;");
        prepStmt.setInt(1, user.getUserId());
        prepStmt.setInt(2, achievement.getId());
        ResultSet rs = prepStmt.executeQuery();
        return rs.next();
    }

    public Achievement getAchievementById(int id) throws SQLException {
        PreparedStatement prepStmt = conn.prepareStatement("SELECT * FROM achievements" +
                " WHERE id = ?");
        prepStmt.setInt(1, id);
        ResultSet rs = prepStmt.executeQuery();
        if(rs.next()){
            Achievement achievement = new Achievement();
            achievement.setId(rs.getInt("id"));
            achievement.setTitle(rs.getString("title"));
            achievement.setDescription(rs.getString("description"));
            achievement.setIconUrl(rs.getString("icon_url"));
            return achievement;
        }
        return null;
    }
}
