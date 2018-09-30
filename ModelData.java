package com.example.gearquicker.hydrodinamicsimulator;

import java.util.ArrayList;
import java.util.List;

public class ModelData  {

    private static volatile ModelData instance;

    double layerWidth;
    double porosity;
    double volume_factor;
    double viscosity;
    double compressibility;
    double permeability;
    double pressure;
    List<Well> wells;
    double[][][] map;
    int currentT;
    double minP;
    double maxP;

    double curentDebit;

    public ModelData() {
        layerWidth = 10;
        porosity = 0.2;
        volume_factor = 1;
        viscosity = 5;
        compressibility = 0.00005;
        permeability = 100;
        pressure = 200;
        wells = new ArrayList<>();
        map = new double[20][20][100];
        minP = pressure;
        maxP = pressure;
        currentT = 0;

        curentDebit = 100;
    }

    public static ModelData getInstance() {
        if (instance == null) {
            synchronized (ModelData.class) {
                if (instance == null) {
                    instance = new ModelData();
                }
            }
        }
        return instance;
    }


    public double getLayerWidth() {
        return layerWidth;
    }

    public void setLayerWidth(double layerWidth) {
        this.layerWidth = layerWidth;
    }

    public double getPorosity() {
        return porosity;
    }

    public void setPorosity(double porosity) {
        this.porosity = porosity;
    }

    public double getVolume_factor() {
        return volume_factor;
    }

    public void setVolume_factor(double volume_factor) {
        this.volume_factor = volume_factor;
    }

    public double getViscosity() {
        return viscosity;
    }

    public void setViscosity(double viscosity) {
        this.viscosity = viscosity;
    }

    public double getCompressibility() {
        return compressibility;
    }

    public void setCompressibility(double compressibility) {
        this.compressibility = compressibility;
    }

    public double getPermeability() {
        return permeability;
    }

    public void setPermeability(double permeability) {
        this.permeability = permeability;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
        minP = pressure;
        maxP = pressure;
    }

    public List<Well> getWells() {
        return wells;
    }

    public void setWells(List<Well> wells) {
        this.wells = wells;
    }

    public double[][][] getMap() {
        return map;
    }

    public void setMap(double[][][] map) {
        this.map = map;
    }

    public double getMinP() {
        return minP;
    }

    public void setMinP(double minP) {
        this.minP = minP;
    }

    public double getMaxP() {
        return maxP;
    }

    public void setMaxP(double maxP) {
        this.maxP = maxP;
    }

    public double getCurentDebit() {
        return curentDebit;
    }

    public void setCurentDebit(double curentDebit) {
        this.curentDebit = curentDebit;
    }

    public int getCurrentT() {
        return currentT;
    }

    public void setCurrentT(int currentT) {
        this.currentT = currentT;
    }
}
