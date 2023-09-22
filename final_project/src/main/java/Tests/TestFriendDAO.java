package Tests;

import DAOs.AccountDAO;
import DAOs.FriendDAO;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.criteria.CriteriaBuilder;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class TestFriendDAO {
    private final AccountDAO accDao = new AccountDAO();
    private final FriendDAO frDao = new FriendDAO();

    private int i = 1;

    @BeforeClass
    public void addAccounts() throws SQLException, NoSuchAlgorithmException {
        accDao.addAccount("Zuka1", "Bayern1");
        accDao.addAccount("Zuka2", "Bayern2");
        accDao.addAccount("Zuka3", "Kvicha Kvaratskhelia");
        accDao.addAccount("Paolo", "123");
        while(accDao.accountUsernameExists(Integer.toString(i))){
            i++;
        }
        accDao.addAccount(Integer.toString(i), "123");
        accDao.addAccount(Integer.toString(i + 1), "123");
    }

    @Test
    public void testAddFriend() throws SQLException {
        assertFalse(frDao.addFriend("Zuka1", "Zuka1"));
        if(!frDao.areFriends("Zuka1", "Zuka2")){
            assertTrue(frDao.addFriend("Zuka1", "Zuka2"));
        }
        assertFalse(frDao.addFriend("Zuka1", "Zuka2"));
        if(!frDao.areFriends("Zuka1", "Zuka3")){
            assertTrue(frDao.addFriend("Zuka1", "Zuka3"));
        }
        assertFalse(frDao.addFriend("Zuka1", "Meraba0"));
        assertTrue(frDao.addFriend(Integer.toString(i), Integer.toString(i + 1)));
    }

    @Test
    public void testGetFriends() throws SQLException {
        List<Integer> expectedFriends = new ArrayList<>();
        assertEquals(frDao.getFriends("Paolo"), expectedFriends);
        expectedFriends.add(accDao.getAccountIdByUsername("Zuka2"));
        expectedFriends.add(accDao.getAccountIdByUsername("Zuka3"));
        assertEquals(frDao.getFriends("Zuka1"), expectedFriends);
        expectedFriends.add(accDao.getAccountIdByUsername("Paolo"));
        assertNotEquals(frDao.getFriends("Zuka1"), expectedFriends);
    }

    @Test
    public void testAreFriends() throws SQLException {
        assertFalse(frDao.areFriends("Zuka2", "Zuka3"));
        assertTrue(frDao.areFriends("Zuka1", "Zuka2"));
    }
}
