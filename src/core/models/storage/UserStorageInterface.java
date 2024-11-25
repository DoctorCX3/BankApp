/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.storage;

import core.models.User;
import java.util.ArrayList;

/**
 *
 * @author JULIAN CASTRO CALVO
 */
public interface UserStorageInterface {
    
    boolean addUser(User user); 
    ArrayList<User> getAllUsers();
    
}
