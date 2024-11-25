/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage.utils;

import core.models.storage.TransactionStorage;
import core.models.transaction.Transaction;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author JULIAN CASTRO CALVO
 */
public class TransactionUtils {
    
    public static ArrayList<Transaction> orderedTransactions(){
        TransactionStorage transactionStorage = TransactionStorage.getInstance();
        ArrayList<Transaction> transactions = transactionStorage.getAllTransactions();
        ArrayList<Transaction> transactionsCopy = (ArrayList<Transaction>) transactions.clone();
        Collections.reverse(transactionsCopy);
        return transactionsCopy;
    }
    
}
