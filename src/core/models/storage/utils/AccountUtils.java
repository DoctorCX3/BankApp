/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage.utils;

import core.models.Account;
import core.models.storage.AccountStorage;
import java.util.ArrayList;

/**
 *
 * @author JULIAN CASTRO CALVO
 */
public class AccountUtils {
    
    public static ArrayList<Account> orderAccountById(){
        
        AccountStorage accountStorage = AccountStorage.getInstance();
        ArrayList<Account> accounts = accountStorage.getAllAccounts();
        
        if (accounts == null){
            accounts = new ArrayList<>();
        }
        
        accounts.sort((account1, account2) -> (account1.getId().compareTo(account2.getId())));
        return accounts;
        
    }
    
}
