/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.transaction.Transaction;
import core.models.transaction.TransactionType;
import core.models.storage.AccountStorage;
import core.models.storage.TransactionStorage;
import core.models.transaction.DepositTransaction;
import core.models.transaction.TransferTransaction;
import core.models.transaction.WithdrawTransaction;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.regex.*;

/**
 *
 * @author JULIAN CASTRO CALVO
 */
public class TransactionController {
    
    public static Response deposit(String destinationAccountId, String amount){
        try{
            AccountStorage accountStorage = AccountStorage.getInstance();
            TransactionStorage transactionStorage = TransactionStorage.getInstance();   
            ArrayList<Account> accounts = accountStorage.getAllAccounts();
            
            /*Verificaciones del ID de la cuenta destino*/
            Account destinationAccount = null;
            if(destinationAccountId == null || destinationAccountId.isEmpty()){
                return new Response("Destination account must be not empty", Status.BAD_REQUEST);
            } else{                
                /* Se hace la validación del formato de número de cuenta */
                String regex = "^\\d{3}-\\d{6}-\\d{2}$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(destinationAccountId);

                 if (!matcher.matches()) {
                    return new Response("Destination account must be in the format", Status.BAD_REQUEST);
                } else {                   
                    for (Account account : accounts) {
                        if (account.getId().equals(destinationAccountId)) {
                            destinationAccount = account;
                        }
                    }
                    if (destinationAccount == null || destinationAccountId.isEmpty()) {
                        return new Response("Destination account not found", Status.BAD_REQUEST);
                    }
                }
            }
                        
            /*Se verifica el importe de dinero*/            
            double amountDouble;
            try{ 
                if (amount.equals("")){
                    return new Response("Amount must be not empty", Status.BAD_REQUEST);
                }
                
                amountDouble = Double.parseDouble(amount);
                if (amountDouble <= 0){
                    return new Response("Amount must be greater than zero", Status.BAD_REQUEST);
                }
            }catch (NumberFormatException ex){
                return new Response("Amount must be numeric", Status.BAD_REQUEST);
            }            
            
            /*Se crea una transacción de depósito, la ejecuta depositando el monto en 
            la cuenta de destino, y luego la almacena en el sistema*/
            Transaction transaction = new Transaction(new DepositTransaction(), null, destinationAccount, amountDouble);
            transaction.getMethod().execute(null, transaction.getDestinationAccount(), transaction.getAmount());
            transactionStorage.executeTransaction(transaction);
            
            return new Response("Transaction Successful!", Status.OK, transaction);
        } catch (Exception ex) {
            return new Response("Unexpected Error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response withdraw(String sourceAccountId, String amount){ /*Retirar*/
        try{
            AccountStorage accountStorage = AccountStorage.getInstance();
            TransactionStorage transactionStorage = TransactionStorage.getInstance();
            ArrayList<Account> accounts = accountStorage.getAllAccounts();
            
            /*Verificaciones del ID de la cuenta de origen*/
            Account sourceAccount = null;
            if(sourceAccountId == null || sourceAccountId.isEmpty()){
                return new Response("Source account must be not empty", Status.BAD_REQUEST);
            } else{                
                /* Se hace la validación del formato de número de cuenta */
                String regex = "^\\d{3}-\\d{6}-\\d{2}$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(sourceAccountId);

                 if (!matcher.matches()) {
                    return new Response("Source account must be in the format", Status.BAD_REQUEST);
                } else {                    
                    for (Account account : accounts) {
                        if (account.getId().equals(sourceAccountId)) {
                            sourceAccount = account;
                        }
                    }
                    if (sourceAccount == null || sourceAccountId.isEmpty()) {
                        return new Response("Destination account not found", Status.BAD_REQUEST);
                    }
                }
            }
            
            /*Se verifica el importe de dinero*/ 
            double amountDouble;
            try{ 
                if (amount.equals("")){
                    return new Response("Amount must be not empty", Status.BAD_REQUEST);
                }
                
                amountDouble = Double.parseDouble(amount);
                if (amountDouble <= 0){
                    return new Response("Amount must be greater than zero", Status.BAD_REQUEST);
                }
                
                if (amountDouble > sourceAccount.getBalance()){
                    return new Response("Insufficient funds", Status.BAD_REQUEST);
                }                
            }catch (NumberFormatException ex){
                return new Response("Amount must be numeric", Status.BAD_REQUEST);
            }
            
            /*Se crea una transacción de retiro, la ejecuta retirando el monto en 
            la cuenta de origen, y luego la almacena en el sistema*/
            Transaction transaction = new Transaction(new WithdrawTransaction(), sourceAccount, null, amountDouble);
            transaction.getMethod().execute(transaction.getSourceAccount(), null, transaction.getAmount());
            transactionStorage.executeTransaction(transaction);
            
            return new Response("Transaction Successful!", Status.OK, transaction);
        } catch (Exception ex) {
            return new Response("Unexpected Error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response transfer(String sourceAccountId, String destinationAccountId, String amount){
        try{
            AccountStorage accountStorage = AccountStorage.getInstance();
            TransactionStorage transactionStorage = TransactionStorage.getInstance(); 
            ArrayList<Account> accounts = accountStorage.getAllAccounts();
            
            /*Verificaciones del ID de ambas cuentas*/
            Account destinationAccount = null;
            if(destinationAccountId == null || destinationAccountId.isEmpty()){
                return new Response("Destination account must be not empty", Status.BAD_REQUEST);
            } else{                
                /* Se hace la validación del formato de número de cuenta */
                String regex = "^\\d{3}-\\d{6}-\\d{2}$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(destinationAccountId);

                 if (!matcher.matches()) {
                    return new Response("Destination account must be in the format", Status.BAD_REQUEST);
                } else {                    
                    for (Account account : accounts) {
                        if (account.getId().equals(destinationAccountId)) {
                            destinationAccount = account;
                        }
                    }
                    if (destinationAccount == null || destinationAccountId.isEmpty()) {
                        return new Response("Destination account not found", Status.BAD_REQUEST);
                    }
                }
            }
            
            Account sourceAccount = null;
            if(sourceAccountId == null || sourceAccountId.isEmpty()){
                return new Response("Source account must be not empty", Status.BAD_REQUEST);
            } else{                
                /* Se hace la validación del formato de número de cuenta */
                String regex = "^\\d{3}-\\d{6}-\\d{2}$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(sourceAccountId);

                 if (!matcher.matches()) {
                    return new Response("Source account must be in the format", Status.BAD_REQUEST);
                } else {                    
                    for (Account account : accounts) {
                        if (account.getId().equals(sourceAccountId)) {
                            sourceAccount = account;
                        }
                    }
                    if (sourceAccount == null || sourceAccountId.isEmpty()) {
                        return new Response("Destination account not found", Status.BAD_REQUEST);
                    }
                }
            }
            
            /*No se puede transferir de una cuenta a si misma*/
            if (sourceAccount == destinationAccount){
                return new Response("Cannot transfer from an account to itself", Status.BAD_REQUEST);
            }         
            
            /*Se verifica el importe de dinero*/
            double amountDouble;
            try{ /**/
                if (amount.equals("")){
                    return new Response("Amount must be not empty", Status.BAD_REQUEST);
                }
                
                amountDouble = Double.parseDouble(amount);
                if (amountDouble <= 0){
                    return new Response("Amount must be greater than zero", Status.BAD_REQUEST);
                }
                
                if (amountDouble > sourceAccount.getBalance()){
                    return new Response("Insufficient funds", Status.BAD_REQUEST);
                }                
            }catch (NumberFormatException ex){
                return new Response("Amount must be numeric", Status.BAD_REQUEST);
            }
            
            /*Se crea una transacción de transferencia, la ejecuta depositando el monto en 
            la cuenta de destino y retirandolo de la cuenta de origen, y luego la almacena en el sistema*/
            Transaction transaction = new Transaction(new TransferTransaction(), sourceAccount, destinationAccount, amountDouble);
            transaction.getMethod().execute(transaction.getSourceAccount(), transaction.getDestinationAccount(), transaction.getAmount());
            transactionStorage.executeTransaction(transaction);
            
            return new Response("Transaction Successful!", Status.OK, transaction);
        } catch (Exception ex) {
            return new Response("Unexpected Error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
}
