/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.transaction;

import core.models.Account;

/**
 *
 * @author JULIAN CASTRO CALVO
 */
public interface TransactionMethodInterface {
    
    void execute(Account sourceAccount, Account destinationAccount, double amount);
    
    TransactionType getTransactionType();
    
}

/*
Es una interfaz que define cómo realizar una transacción. 
Básicamente, dice que cualquier clase que implemente esta interfaz debe tener:

1. Un método para ejecutar la transacción (execute).
2. Un método para indicar qué tipo de transacción es (getTransactionType).
*/