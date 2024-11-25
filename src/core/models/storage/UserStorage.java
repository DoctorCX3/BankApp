/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.User;
import java.util.ArrayList;

/**
 *
 * @author JULIAN CASTRO CALVO
 */
public class UserStorage extends Storage implements UserStorageInterface {
    
    private static UserStorage instance;
    private ArrayList<User> users;
    
    private UserStorage(){ 
        this.users = new ArrayList<>();
    }
    
    public static UserStorage getInstance(){ 
        if (instance == null){
            instance = new UserStorage();
        }
        return instance;
    }
    
    @Override
    public boolean addUser(User user){
        for (User u : this.users){
            if (u.getId() == user.getId()){
                return false;
            }
        }
        this.users.add(user);
        return true;
    }
    
    @Override
    public ArrayList<User> getAllUsers(){ 
        return users;
    }
    
    public ArrayList<User> orderUserById(){ 
        users.sort((user1, user2) -> (user1.getId() - user2.getId()));
        return users;
    }
    
}
