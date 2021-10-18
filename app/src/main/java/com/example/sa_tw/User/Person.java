package com.example.sa_tw.User;

public class Person {
    private Person(){}
    private static Person person = new Person();
    private String name;
    private String name_id;
    private String email;
    private String acc;


    public void setName(String name){
        this.name=name;
    }
    public void setName_id(String name_id){
        this.name_id=name_id;
    }
    public void setAcc(String acc){
        this.acc=acc;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getAcc(){
        return acc;
    }
    public String getEmail(){
        return email;
    }
    public String getName(){
        return name;
    }
    public String getName_id(){
        return name_id;
    }
    public static Person getInstance(){
        return person;
    }

}
