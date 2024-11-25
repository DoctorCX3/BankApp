/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.storage.TransactionStorage;
import core.models.storage.utils.TransactionUtils;
import core.models.transaction.Transaction;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JULIAN CASTRO CALVO
 */
public class ListTransactionsController {
    
    public static Response refreshTransactions(DefaultTableModel model){
        try{
            TransactionStorage transactionStorage = TransactionStorage.getInstance();
            ArrayList<Transaction> transactionsCopy = TransactionUtils.orderedTransactions();
            
            /*Se actualiza el modelo de la tabla con los datos del tipo de transaccion, cuanta de origen, cuenta de destino y el dinero a mover*/
            if (!transactionsCopy.isEmpty()){
                model.setRowCount(0);
                for (Transaction transaction : transactionsCopy) {
                    model.addRow(new Object[]{ transaction.getType().name(), (transaction.getSourceAccount() != null ? transaction.getSourceAccount().getId() : "None"), (transaction.getDestinationAccount() != null ? transaction.getDestinationAccount().getId() : "None"), transaction.getAmount() });
                }
                return new Response("Transaction list correctly updated", Status.OK);
            }else {
                return new Response("Transaction list is empety", Status.NO_CONTENT);
            }
        }catch (Exception ex){
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
}
