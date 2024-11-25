/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.linkAccount;

import core.models.Account;
import core.models.User;

/**
 *
 * @author JULIAN CASTRO CALVO
 */
public class AccountLinker {
    
    public static void linkAccoutnToUser(Account account, User user){
        user.addAccount(account);
    }
    
}
