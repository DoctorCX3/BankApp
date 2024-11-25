/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.storage.AccountStorage;
import core.models.storage.utils.AccountUtils;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JULIAN CASTRO CALVO
 */
public class ListAccountsController {
    
    public static Response refreshAccounts(DefaultTableModel model){
        try{
            AccountStorage accountStorage = AccountStorage.getInstance();
            ArrayList<Account> accounts = AccountUtils.orderAccountById();
            
            /*Se actualiza el modelo de la tabla con los datos de ID, propietario y saldo de cada cuenta*/
            if (!accounts.isEmpty()){
                model.setRowCount(0);
                for (Account account : accounts) {
                    model.addRow(new Object[]{account.getId(), account.getOwner().getId(), account.getBalance()});
                }
                return new Response("Account list correctly updated", Status.OK);
            }else {
                return new Response("Account list is empety", Status.NO_CONTENT);
            }
        }catch (Exception ex){
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}

