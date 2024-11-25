/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Account;
import java.util.ArrayList;

/**
 *
 * @author JULIAN CASTRO CALVO
 */
public interface AccountStorageInterface {
    
    boolean createAccount(Account account);
    ArrayList<Account> getAllAccounts();
    
}
