package DAOs;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {
        AccountDAO dao = new AccountDAO();
        dao.addAccount("acc1", "acc1");
    }
}
