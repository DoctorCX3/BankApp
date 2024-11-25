/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.transaction.Transaction;
import java.util.ArrayList;

/**
 *
 * @author JULIAN CASTRO CALVO
 */
public class TransactionStorage extends Storage implements TransactionStorageInterface{
    
    private static TransactionStorage instance; 
    private ArrayList<Transaction> transactions;
    
    private TransactionStorage(){ 
        this.transactions = new ArrayList<>();
    }
    
    public static TransactionStorage getInstance(){ 
        if (instance == null){
            instance = new TransactionStorage();
        }
        return instance;
    }   
    
    @Override
    public Transaction executeTransaction(Transaction transaction){ 
        if (this.transactions == null){
            this.transactions = new ArrayList<>();
        }
        this.transactions.add(transaction);
        return transaction;
    }    
    
    @Override
    public ArrayList<Transaction> getAllTransactions() { 
        return this.transactions; 
    }
    
}
