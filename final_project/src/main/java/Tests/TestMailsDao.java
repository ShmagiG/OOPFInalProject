package Tests;

import DAOs.AccountDAO;
import DAOs.MailsDao;
import ObjectClasses.User;
import org.testng.annotations.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class TestMailsDao{
    private final AccountDAO accDao = new AccountDAO();
    private final MailsDao mailsDao = new MailsDao();
    private int i = 1;

    @Test
    public void testAddMail() throws SQLException, NoSuchAlgorithmException {
        assertFalse(mailsDao.addMail("","" ,"" ,"", 1));
        assertFalse(mailsDao.addMail("Giorgi123","Rezi" ,"" ,"", 1));
        assertFalse(mailsDao.addMail("","Rezi" , MailsDao.ADD_FRIEND ,"", 1));
        assertFalse(mailsDao.addMail("Giorgi123","Rezi" , "Other" ,"", 1));

        accDao.addAccount("Giorgi123", "Giorgi123");
        accDao.addAccount("Rezi", "Rezi");

        assertFalse(mailsDao.addMail("Giorgi123","Levan" , MailsDao.ADD_FRIEND ,"", 1));
        assertFalse(mailsDao.addMail("Gubaz","Rezi" , MailsDao.ADD_FRIEND ,"", 1));

        assertTrue(mailsDao.addMail("Giorgi123","Rezi" , MailsDao.ADD_FRIEND ,"add me as friend"));
        assertTrue(mailsDao.addMail("Giorgi123","Rezi" , MailsDao.MESSAGE ,"Hello"));
        mailsDao.removeMail(mailsDao.getReceivedMailsForUser("Rezi").get(0).getId());
        mailsDao.removeMail(mailsDao.getReceivedMailsForUser("Rezi").get(0).getId());

    }


    @Test
    public void testRemoveMail() throws SQLException, NoSuchAlgorithmException {
        accDao.addAccount("Giorgi123", "Giorgi123");
        accDao.addAccount("Rezi", "Rezi");

        mailsDao.addMail("Giorgi123","Rezi" , MailsDao.ADD_FRIEND ,"add me as friend");
        mailsDao.addMail("Giorgi123","Rezi" , MailsDao.MESSAGE ,"Hello");

        assertEquals( mailsDao.getReceivedMailsForUser("Rezi").size(), 2);
        mailsDao.removeMail(mailsDao.getReceivedMailsForUser("Rezi").get(0).getId());
        assertEquals( mailsDao.getReceivedMailsForUser("Rezi").size(), 1);
        mailsDao.removeMail(mailsDao.getReceivedMailsForUser("Rezi").get(0).getId());
        assertEquals(mailsDao.getReceivedMailsForUser("Rezi").size(), 0);

    }

    @Test
    public void testGetReceivedMailForUsers() throws SQLException, NoSuchAlgorithmException {
        accDao.addAccount("Giorgi123", "Giorgi123");
        accDao.addAccount("Rezi", "Rezi");

        mailsDao.addMail("Giorgi123","Rezi" , MailsDao.ADD_FRIEND ,"add me as friend");
        mailsDao.addMail("Giorgi123","Rezi" , MailsDao.MESSAGE ,"Hello");

        assertEquals( mailsDao.getReceivedMailsForUser("Rezi").size(), 2);
        assertEquals( mailsDao.getReceivedMailsForUser("Giorgi123").size(), 0);

        assertEquals( mailsDao.getReceivedMailsForUser("Rezi").get(0).getSender(), "Giorgi123");
        assertEquals(mailsDao.getReceivedMailsForUser("Rezi").get(0).getMessage(), "add me as friend");
        assertEquals( mailsDao.getReceivedMailsForUser("Rezi").get(0).getType(), MailsDao.ADD_FRIEND);

        mailsDao.removeMail(mailsDao.getReceivedMailsForUser("Rezi").get(0).getId());
        mailsDao.removeMail(mailsDao.getReceivedMailsForUser("Rezi").get(0).getId());

    }


}
