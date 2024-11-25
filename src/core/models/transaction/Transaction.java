/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.transaction;

import core.models.Account;

/**
 *
 * @author edangulo
 */
public class Transaction {
    
    private TransactionType type;
    private Account sourceAccount;
    private Account destinationAccount;
    private double amount;
    private TransactionMethodInterface method;
    
    public Transaction(TransactionMethodInterface method, Account sourceAccount, Account destinationAccount, double amount) {
        this.method = method;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.type = method.getTransactionType();
    }

    public TransactionType getType() { 
        return type;
    }
    
    public TransactionMethodInterface getMethod(){ 
        return method;
    }

    public Account getSourceAccount() { 
        return sourceAccount;
    }

    public Account getDestinationAccount() { 
        return destinationAccount;
    }

    public double getAmount() {
        return amount;
    }
    
}
