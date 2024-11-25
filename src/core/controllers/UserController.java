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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author JULIAN CASTRO CALVO
 */
public class UserController {
    
    public static ArrayList<User> users = new ArrayList<>();
    
    public static Response registerUser(String id, String firstname, String lastname, String age){
        try{
            UserStorage userStorage = UserStorage.getInstance();
            ArrayList<User> users = userStorage.getAllUsers();
            int idInt, ageInt;
            
            /*Verificaciones del ID*/
            if(id == null || id.isEmpty()){
                return new Response("ID must be not empty", Status.BAD_REQUEST);
            }
            
            try{              
                idInt = Integer.parseInt(id);                  
            }catch (NumberFormatException ex){
                return new Response("ID must be numeric", Status.BAD_REQUEST);
            }
            
            if (idInt < 0){ 
                return new Response("ID must be positive", Status.BAD_REQUEST);
            } 
            
            if (idInt > 999999999){ 
                return new Response("ID must not be longer than 9 digits", Status.BAD_REQUEST);
            }
            
            for(User user : users){ 
                if (user.getId() == idInt){
                    return new Response("A user with that ID already exists", Status.BAD_REQUEST);
                }
            }
            
            /*Verificar que el nombre no sea vacio*/
            if (firstname.equals("")) { 
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }
            
            /*Verificar que el apellido no sea vacio*/
            if (lastname.equals("")) { 
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            
            /*Verificaciones de la edad*/
            if(age == null || age.isEmpty()){
                return new Response("Age must be not empty", Status.BAD_REQUEST);
            }
            
            try {
                ageInt = Integer.parseInt(age);   
            } catch (NumberFormatException ex) {
                return new Response("Age must be numeric", Status.BAD_REQUEST);
            }       
            
            if (ageInt < 18){ 
                return new Response("Age must be over 18", Status.BAD_REQUEST);
            }
            
            if (ageInt > 150){ 
                return new Response("Age must be realistic", Status.BAD_REQUEST);
            }
            
            /*Se crea un nuevo usuario con los datos (ID, nombre, apellido y edad) y lo agrega al sistema*/
            userStorage.addUser(new User(idInt, firstname, lastname, ageInt)); 
            
            return new Response("User created successfully", Status.CREATED);
            
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
            
        }
        
    }

}
