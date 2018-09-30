package com.example.gearquicker.hydrodinamicsimulator;


import java.util.List;

public class Formula {

    double x;
    double y;
    ModelData modelData;

    public Formula(double x, double y) {
        this.x = x;
        this.y = y;
        modelData = ModelData.getInstance();
    }

    public double calc(double t) {
        double P = 0;
        double l;
        List<Well> wells = modelData.getWells();
        for (Well well : wells) {
            l = Math.sqrt((x - well.getX()) * (x - well.getX()) + (y - well.getY()) * (y - well.getY()));
            P = P + (well.getDebit() / t) * GDIMath.besselK0(2 * l * Math.sqrt(t) / 0.1);
        }
        return P;
    }
}
