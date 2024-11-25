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
public class AccountStorage extends Storage implements AccountStorageInterface{
    
    private static AccountStorage instance; 
    private ArrayList<Account> accounts;
    
    private AccountStorage(){ 
         this.accounts = new ArrayList<>();
     }
    
    public static AccountStorage getInstance(){ 
        if (instance == null){
            instance = new AccountStorage();
        }
        return instance;
    }
    
    @Override
    public boolean createAccount(Account account){ 
        for (Account c : this.accounts){
            if (c.getId().equals(account.getId())){
                return false;
            }
        }
        this.accounts.add(account);
        return true;
    }    
    
    @Override
    public ArrayList<Account> getAllAccounts(){ 
        return accounts;
    }
}
