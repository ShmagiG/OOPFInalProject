package UserPage;

import java.sql.Connection;

public class UserManager {
    private Connection friendsDB;
    private Connection skillsDB;
    private int id;

    public UserManager(Connection friendsDB, Connection skillsDB){
        this.friendsDB = friendsDB;
        this.skillsDB = skillsDB;
        id = 1;
    }

    public void addFriend(int userId, String friendName){}
    public void deleteFriend(int userId, String friendName){}

    public void addSkill(String skill){}
}
