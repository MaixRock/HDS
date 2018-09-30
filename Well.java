package com.example.gearquicker.hydrodinamicsimulator;

public class Well {

    double debit;
    float x;
    float y;

    public Well(double debit, float x, float y) {
        this.debit = debit;
        this.x = x;
        this.y = y;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
