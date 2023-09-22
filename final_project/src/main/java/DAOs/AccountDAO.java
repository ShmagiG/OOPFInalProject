package DAOs;

import ObjectClasses.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    private final Connection conn;

    public AccountDAO(){
        this.conn = DBConnection.getConnection();
    }

    public boolean accountUsernameExists(String username) throws SQLException {
        PreparedStatement prepStmt = conn.prepareStatement("SELECT a.id FROM accounts a " +
                "where a.username = ?;");
        prepStmt.setString(1, username);
        ResultSet rs = prepStmt.executeQuery();
        if(rs.next()) return true;
        return false;
    }

    public boolean isAccountValid(String username, String password) throws SQLException, NoSuchAlgorithmException {
        String hash = hashString(password);
        PreparedStatement prepStmt = conn.prepareStatement("SELECT a.id FROM accounts a " +
                "where a.username = ? AND a.password_hash = ?;");
        prepStmt.setString(1, username);
        prepStmt.setString(2, hash);
        ResultSet rs = prepStmt.executeQuery();
        if(rs.next()) return true;
        return false;
    }

    public boolean addAccount(String username, String password) throws SQLException, NoSuchAlgorithmException {
        if(username.trim().length() == 0 || password.trim().length() == 0) return false;
        if(!accountUsernameExists(username)){
            String hash = hashString(password);
            PreparedStatement prepStmt = conn.prepareStatement("INSERT INTO accounts (username, password_hash) " +
                    " VALUES (?, ?);");
            prepStmt.setString(1, username);
            prepStmt.setString(2, hash);
            prepStmt.executeUpdate();
            return true;
        }
        return false;
    }

    public List<User> searchAccountsByUsername(String username) throws SQLException {
        PreparedStatement prepStmt = conn.prepareStatement("SELECT id, username FROM accounts WHERE " +
                "username LIKE ?;");
        prepStmt.setString(1, username + "%");
        ResultSet rs = prepStmt.executeQuery();

        List<User> accounts = new ArrayList<>();

        while(rs.next()){
            User user = new User(rs.getInt("id"), rs.getString("username"));
            accounts.add(user);
        }

        return accounts;
    }

    // returns Account Id by using its username. returns -1 if there is no such account with the
    // passed username
    public int getAccountIdByUsername(String username) throws SQLException {
        AccountDAO accDao = new AccountDAO();
        if(accDao.accountUsernameExists(username)){
            PreparedStatement prepStmt = conn.prepareStatement("SELECT a.id FROM accounts a " +
                    "where a.username = ?;");
            prepStmt.setString(1, username);
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            return rs.getInt("id");
        } else {
            return -1;
        }
    }

    public String getUserNameByAccountId(int user_id) throws SQLException {
        AccountDAO accDao = new AccountDAO();
            PreparedStatement prepStmt = conn.prepareStatement("SELECT a.username FROM accounts a " +
                    "where a.id = ?;");
            prepStmt.setInt(1, user_id);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                return rs.getString("username");
            }
            return null;
    }

    private static String hexToString(byte[] bytes) {
        StringBuffer buff = new StringBuffer();
        for (int i=0; i<bytes.length; i++) {
            int val = bytes[i];
            val = val & 0xff;  // remove higher bits, sign
            if (val<16) buff.append('0'); // leading 0
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }

    private String hashString(String s) throws NoSuchAlgorithmException {
        String result = "";

        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(s.getBytes());

        byte[] digest = md.digest();

        result = hexToString(digest);

        return result;
    }
}