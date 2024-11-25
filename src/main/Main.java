/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import core.controllers.TransactionController;
import core.controllers.utils.Response;
import core.models.Account;
import core.models.User;
import core.models.storage.AccountStorage;
import core.models.storage.UserStorage;
import core.views.BankFrame;

/**
 *
 * @author JULIAN CASTRO CALVO
 */
public class Main {
    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BankFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BankFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BankFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BankFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        /* Crea la vista  */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BankFrame().setVisible(true);
            }
        });
        
    }
    
}
