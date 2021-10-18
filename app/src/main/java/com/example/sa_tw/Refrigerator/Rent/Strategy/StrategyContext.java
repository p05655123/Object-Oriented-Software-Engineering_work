package com.example.sa_tw.Refrigerator.Rent.Strategy;

public class StrategyContext {
    public Strategy s = null;
    public int c;
    public int d;
    public void setStrategy(Strategy s) {
        this.s = s;
    }
    public void setCost(int cost) {
        this.c = cost;
    }
    public void setDays(int days) {
        this.d = days;
    }
    public double getCost() {
        return s.cost(c);
    }
    public int getDays() {
        return s.days(d);
    }
}
