package org.poo.cb;

import java.util.ArrayList;

public class Stock {
    private final String name;
    private final double[] last10Values;

    public double[] getLast10Values() {
        return last10Values;
    }

    public String getName() {
        return name;
    }

    public Stock(String name, double[] last10Values) {
        this.name = name;
        this.last10Values = last10Values;
    }
}
