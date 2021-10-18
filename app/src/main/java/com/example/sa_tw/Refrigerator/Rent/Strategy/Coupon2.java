package com.example.sa_tw.Refrigerator.Rent.Strategy;

public class Coupon2 implements Strategy {
    public double cost(int c){
        return c;
    };
    public int days(int d){
        return  d + 5;
    };
}
