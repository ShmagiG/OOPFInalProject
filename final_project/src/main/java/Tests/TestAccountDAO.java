package Tests;

import DAOs.AccountDAO;
import ObjectClasses.User;
import org.testng.annotations.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class TestAccountDAO{
    private final AccountDAO accDao = new AccountDAO();
    private int i = 1;

    @Test
    public void testAddAccount() throws SQLException, NoSuchAlgorithmException {
        while(accDao.accountUsernameExists(Integer.toString(i))){
            i++;
        }
        assertTrue(accDao.addAccount(Integer.toString(i), "paroli"));

        if(!accDao.accountUsernameExists("Giorgi")){
            assertTrue(accDao.addAccount("Giorgi", "Giorgi"));
        }
        if(!accDao.accountUsernameExists("Giorgi1")){
            assertTrue(accDao.addAccount("Giorgi1", "Giorgi"));
        }
        if(!accDao.accountUsernameExists("Levani")){
            assertTrue(accDao.addAccount("Levani", "Levani"));
        }
        assertFalse(accDao.addAccount("Giorgi", "Giorgi"));
        assertFalse(accDao.addAccount("", "1"));
    }

    @Test
    public void testIsAccountValid() throws SQLException, NoSuchAlgorithmException {
        assertTrue(accDao.isAccountValid("Giorgi", "Giorgi"));
        assertFalse(accDao.isAccountValid("Giorgi", "asd"));
    }

    @Test
    public void testSearchAccountsByUsername() throws SQLException {
        List<User> usersExpected = new ArrayList<>();
        User user1 = new User(accDao.getAccountIdByUsername("Giorgi"), "Giorgi");
        usersExpected.add(user1);
        User user2 = new User(accDao.getAccountIdByUsername("Giorgi1"), "Giorgi1");
        usersExpected.add(user2);
        List<User> usersReturned = accDao.searchAccountsByUsername("Giorgi");
        assertEquals(usersReturned, usersExpected);
        User user3 = new User(accDao.getAccountIdByUsername("Levani"), "Levani");
        usersExpected.add(user3);
        assertNotEquals(usersReturned, usersExpected);
    }

    @Test
    public void testIdsAndUsernames() throws SQLException {
        assertEquals(-1, accDao.getAccountIdByUsername("Mariam"));
        User user = accDao.searchAccountsByUsername("Levani").get(0);
        assertEquals(user.getUserId(), accDao.getAccountIdByUsername("Levani"));
        assertNotEquals(-1, accDao.getAccountIdByUsername("Levani"));
        assertEquals("Levani", accDao.getUserNameByAccountId(user.getUserId()));
        String xd = accDao.getUserNameByAccountId(-1);
    }
}
