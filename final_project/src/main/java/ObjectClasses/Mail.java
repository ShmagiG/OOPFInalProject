//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ObjectClasses;

import DAOs.AccountDAO;
import java.sql.SQLException;

public class Mail {
    private final String sender;
    private final String receiver;
    private final String type;
    private final String message;

    private final int quiz_id;

    private final int id;

    public Mail(int sender_id, int receiver_id, String type, String message, int id) throws SQLException {
        this(sender_id, receiver_id, type, message, -1, id);
    }

    public Mail (int sender_id, int receiver_id, String type, String message, int quiz_id, int id) throws SQLException {
        AccountDAO accountDAO = new AccountDAO();
        this.sender = accountDAO.getUserNameByAccountId(sender_id);
        this.receiver = accountDAO.getUserNameByAccountId(receiver_id);
        this.type = type;
        this.message = message;
        this.quiz_id = quiz_id;
        this.id = id;
    }

    public String getSender() {
        return this.sender;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public String getMessage() {
        return this.message;
    }

    public String getType() {
        return this.type;
    }

    public int getId() {
        return id;
    }
}
