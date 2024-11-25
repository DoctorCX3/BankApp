/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.storage;

import core.models.transaction.Transaction;
import java.util.ArrayList;

/**
 *
 * @author JULIAN CASTRO CALVO
 */
interface TransactionStorageInterface {
    
    Transaction executeTransaction(Transaction transaction);
    ArrayList<Transaction> getAllTransactions();
    
}
