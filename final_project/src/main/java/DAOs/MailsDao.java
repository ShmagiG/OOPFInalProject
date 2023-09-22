//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package DAOs;

import ObjectClasses.Mail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MailsDao {
    public static final String ADD_FRIEND = "addFriend";
    public static final String CHALLENGE_QUIZ = "challengeQuiz";
    public static final String MESSAGE = "message";
    private final Connection conn = DBConnection.getConnection();

    public MailsDao() {
    }

    public List<Mail> getReceivedMailsForUser(String userName) throws SQLException {
        AccountDAO accDao = new AccountDAO();
        PreparedStatement prepStmt = this.conn.prepareStatement("SELECT * FROM mails WHERE receiver_id = ?;");
        prepStmt.setInt(1, accDao.getAccountIdByUsername(userName));
        ResultSet rs = prepStmt.executeQuery();
        List<Mail> mails = new ArrayList();

        while(rs.next()) {
            Mail mail = new Mail(rs.getInt("sender_id"), rs.getInt("receiver_id"), rs.getString("mail_type"), rs.getString("message"),rs.getInt("quiz_id"), rs.getInt("id"));
            mails.add(mail);
        }

        return mails;
    }

    public boolean addMail(String senderUserName, String receiverUserName, String mailType, String message) throws SQLException {
        return addMail(senderUserName, receiverUserName, mailType, message, -1);
    }

    public boolean addMail(String senderUserName, String receiverUserName, String mailType, String message, int quiz_id) throws SQLException {
        if (senderUserName.trim().length() != 0 && receiverUserName.trim().length() != 0 && mailType.trim().length() != 0) {
            if (!mailType.equals("addFriend") && !mailType.equals("challengeQuiz") && !mailType.equals("message")) {
                return false;
            } else {
                AccountDAO accDao = new AccountDAO();
                if (accDao.accountUsernameExists(senderUserName) && accDao.accountUsernameExists(receiverUserName)) {
                    PreparedStatement prepStmt = this.conn.prepareStatement("INSERT INTO mails (sender_id, receiver_id, mail_type, message, quiz_id)  VALUES (?, ?, ?, ?, ?);");
                    prepStmt.setInt(1, accDao.getAccountIdByUsername(senderUserName));
                    prepStmt.setInt(2, accDao.getAccountIdByUsername(receiverUserName));
                    prepStmt.setString(3, mailType);
                    prepStmt.setString(4, message);
                    prepStmt.setInt(5, quiz_id);
                    prepStmt.executeUpdate();
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    public void removeMail(int mail_id) throws SQLException {
        PreparedStatement prepStmt = this.conn.prepareStatement("DELETE FROM mails WHERE id = ?;");
        prepStmt.setInt(1, mail_id);
        prepStmt.executeUpdate();
    }
}
