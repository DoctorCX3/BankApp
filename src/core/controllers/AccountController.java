/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.User;
import core.models.linkAccount.AccountLinker;
import core.models.storage.AccountStorage;
import core.models.storage.UserStorage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JULIAN CASTRO CALVO
 */
public class AccountController {
    
    public static Response createAccount(String userId, String initialBalance){
        try{
            UserStorage userStorage = UserStorage.getInstance();
            ArrayList<User> users = userStorage.getAllUsers();
            AccountStorage accountStorage = AccountStorage.getInstance();
            ArrayList<Account> accounts = accountStorage.getAllAccounts(); 
            int userIdInt;
            double initialBalanceDouble;
            
            Random random = new Random();
            int first = random.nextInt(1000);
            int second = random.nextInt(1000000);
            int third = random.nextInt(100);
            
            /*Verificar que el ID usuario*/
            if(userId == null || userId.isEmpty()){
                return new Response("ID must be not empty", Status.BAD_REQUEST);
            }
            
            try{ 
                userIdInt = Integer.parseInt(userId);         
            }catch (NumberFormatException ex){
                return new Response("ID must be numeric", Status.BAD_REQUEST);
            }
            
            if (userIdInt < 0){ 
                    return new Response("ID must be positive", Status.BAD_REQUEST);
                }
            if (userIdInt > 999999999){
                return new Response("ID must not be longer than 9 digits", Status.BAD_REQUEST);
            } 
            
            /*Verificar el balance inicial*/ 
            try{ 
                initialBalanceDouble = Double.parseDouble(initialBalance);
                if (initialBalanceDouble < 0){
                    return new Response("Initial balance must be positive", Status.BAD_REQUEST);
                }                
            }catch (NumberFormatException ex){
                return new Response("Initial balance must be numeric", Status.BAD_REQUEST);
            }
            
            /*Verificar que solo se pueda crear cuentas con usuarios previamente registrados*/
            User selectedUser = null;
            for (User user : users) {
                if (user.getId() == userIdInt) {
                    selectedUser = user;  

                }
            }

            if (selectedUser == null) {
                return new Response("Accounts can only be created by registered users", Status.NOT_FOUND);
            }
            
            /*Crear el ID de la cuenta*/
            String accountId = String.format("%03d", first) + "-" + String.format("%06d", second) + "-" + String.format("%02d", third);
            
            /*Verificar que el ID no este repetido*/
            for (Account account : accounts){ 
                if(account.getId().equals(accountId)){
                    return new Response("A account with that ID already exists", Status.BAD_REQUEST);
                }
            }
            
            /*Se crea una nueva cuenta bancaria con un ID único y saldo inicial, 
            se vincula al usuario seleccionado y la almacena en el sistema*/
            
            Account newAccount = new Account(accountId, selectedUser, initialBalanceDouble);
            AccountLinker.linkAccoutnToUser(newAccount, selectedUser);
            accountStorage.createAccount(newAccount);        
            
            return new Response("Account created successfully", Status.CREATED);
            
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
        
    }   
    
}
    
    
    

