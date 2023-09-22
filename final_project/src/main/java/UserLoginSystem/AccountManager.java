package UserLoginSystem;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import javax.resource.cci.ResultSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
//import java.sql.ResultSet;

public class AccountManager {
    private Connection accountsDB;
    private Connection skillsDB;

    public AccountManager(Connection accountsDB, Connection skillsDB){
        this.accountsDB = accountsDB;
        this.skillsDB = skillsDB;
    }

    public boolean accountExists(MysqlxDatatypes.Scalar.String name){
        return false;
    }
    public boolean enterAccount(MysqlxDatatypes.Scalar.String name, MysqlxDatatypes.Scalar.String password){
        return false;
    }
    public void addAccount(MysqlxDatatypes.Scalar.String name, MysqlxDatatypes.Scalar.String password){ }


    public ResultSet searchPeopleBySkill(MysqlxDatatypes.Scalar.String skill){
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
