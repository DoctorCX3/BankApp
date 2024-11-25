/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.User;
import core.models.storage.UserStorage;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JULIAN CASTRO CALVO
 */
public class ListUsersController {
    
    public static Response refreshUsers(DefaultTableModel model){
        try {
            UserStorage userStorage = UserStorage.getInstance();
            ArrayList<User> users = userStorage.orderUserById();
            
            /*Se actualiza el modelo de la tabla con los datos de ID, nombre, apellido y edad*/
            if (!users.isEmpty()){
                model.setRowCount(0);
                for (User user : users) {
                    model.addRow(new Object[]{user.getId(), user.getFirstname() + " " + user.getLastname(), user.getAge(), user.getNumAccounts()});
                }
                return new Response("User list correctly updated", Status.OK);
            }else {
                return new Response("User list is empety", Status.NO_CONTENT);
            }
        }catch (Exception ex){
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
}
