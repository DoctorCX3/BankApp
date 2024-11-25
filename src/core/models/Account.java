/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

/**
 *
 * @author edangulo
 */
public class Account implements AccountInterface{
    
    private String id;
    private User owner;
    private double balance;

    public Account(String id, User owner) {
        this.id = id;
        this.owner = owner;
        this.balance = 0;
        
        /*this.owner.addAccount(this);*/
    }
    
    public Account(String id, User owner, double balance) {
        this.id = id;
        this.owner = owner;
        this.balance = balance;
        
        /*this.owner.addAccount(this);*/
    }
    
    public String getId() { /**/
        return id;
    }

    public User getOwner() { /**/
        return owner;
    }
    
    @Override
    public double getBalance() { /**/
        return balance;
    }
        
    @Override
    public void deposit(double amount) { /**/
        balance += amount;
    }
    
    @Override
    public void withdraw(double amount) { /**/
        balance -= amount;
    }
    
}
