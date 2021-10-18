package com.example.sa_tw.Refrigerator.Rent.Strategy;

public class Coupon1 implements Strategy {
    public double cost(int c){
        return  c * 0.9;
    };
    public int days(int d){
        return  d;
    };
}
