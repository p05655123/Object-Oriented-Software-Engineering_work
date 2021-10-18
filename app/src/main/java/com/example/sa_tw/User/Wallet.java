package com.example.sa_tw.User;

import com.example.sa_tw.Mediator.Mediator;

public class Wallet {
    private Mediator mediator = Mediator.getInstance();
    private int balance;
    private static  Wallet wallet = new Wallet();
    private Wallet(){}

    public void setBalance(int balance){
        this.balance=balance;
    }
    public int getBalance(){
        return balance;
    }
    public void deposit(int money){
        balance+=money;
        mediator.update_wallet();
    }
    public void withdraw(int money){
        balance-=money;
        mediator.update_wallet2();
    }
    public static Wallet getInstance(){
        return wallet;
    }
}
