/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.transaction;

import core.models.Account;

/**
 *
 * @author JULIAN CASTRO CALVO
 */
public class WithdrawTransaction implements TransactionMethodInterface{
    
    @Override
    public void execute(Account sourceAccount, Account destinationAccount, double amount) {
        sourceAccount.withdraw(amount);
    }

    @Override
    public TransactionType getTransactionType() {
        return TransactionType.WITHDRAW;
    }
    
}