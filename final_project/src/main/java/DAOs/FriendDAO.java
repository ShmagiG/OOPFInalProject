package DAOs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendDAO {
    private final Connection conn;

    public FriendDAO(){
        conn = DBConnection.getConnection();
    }

    public boolean addFriend(String friend1, String friend2) throws SQLException {
        if(friend1.equals(friend2)) return false;

        if(areFriends(friend1, friend2)){
            return false;
        } else {
            AccountDAO accDao = new AccountDAO();

            int friendId1 = accDao.getAccountIdByUsername(friend1);
            int friendId2 = accDao.getAccountIdByUsername(friend2);

            if(friendId1 == -1 || friendId2 == -1) return false;

            PreparedStatement prepStmt = conn.prepareStatement("INSERT INTO friends (friend_id1, friend_id2) " +
                    " VALUES (?, ?);");
            prepStmt.setInt(1, friendId1);
            prepStmt.setInt(2, friendId2);
            prepStmt.executeUpdate();

            prepStmt = conn.prepareStatement("INSERT INTO friends (friend_id1, friend_id2) " +
                    " VALUES (?, ?);");
            prepStmt.setInt(1, friendId2);
            prepStmt.setInt(2, friendId1);
            prepStmt.executeUpdate();

            return true;
        }
    }

    // ikos jer idebze ro rame shevcvli
    public List<Integer> getFriends(String username) throws SQLException {
        AccountDAO accDao = new AccountDAO();
        int id = accDao.getAccountIdByUsername(username);

        PreparedStatement prepStmt = conn.prepareStatement("SELECT f.friend_id2 FROM friends f " +
                "WHERE f.friend_id1 = ?;");
        prepStmt.setInt(1, id);
        ResultSet rs = prepStmt.executeQuery();

        List<Integer> ids = new ArrayList<>();

        while(rs.next()){
            ids.add(rs.getInt("friend_id2"));
        }

        return ids;
    }

    public boolean areFriends(String friend1, String friend2) throws SQLException {
        AccountDAO accDao = new AccountDAO();

        int friendId1 = accDao.getAccountIdByUsername(friend1);
        int friendId2 = accDao.getAccountIdByUsername(friend2);

        PreparedStatement prepStmt = conn.prepareStatement("SELECT f.id FROM friends f " +
                "WHERE f.friend_id1 = ? AND f.friend_id2 = ?;");
        prepStmt.setInt(1, friendId1);
        prepStmt.setInt(2, friendId2);
        ResultSet rs = prepStmt.executeQuery();
        if(rs.next()) return true;
        return false;
    }
}