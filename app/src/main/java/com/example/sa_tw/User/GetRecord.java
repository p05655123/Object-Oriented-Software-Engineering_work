package com.example.sa_tw.User;

import java.util.ArrayList;

public class GetRecord {
    private GetRecord(){}
    private static GetRecord getrecord = new GetRecord();
    private ArrayList<String> size_id = new ArrayList<String>();
    private ArrayList<String> place = new ArrayList<String>();
    private ArrayList<String> date = new ArrayList<String>();
    private ArrayList<String> countdown = new ArrayList<String>();



    public void setSize_id(ArrayList array){
        this.size_id = array;
    }
    public void setPlace(ArrayList array){
        this.place = array;
    }
    public void setDate(ArrayList array){
        this.date = array;
    }
    public void setCountdown(ArrayList array){
        this.countdown = array;
    }


    public ArrayList getSize_id(){
        return size_id;
    }
    public ArrayList getPlace(){
        return place;
    }
    public ArrayList getDate(){
        return date;
    }
    public ArrayList getCountdown(){
        return countdown;
    }

    public static GetRecord getInstance(){
        return getrecord;
    }
}
