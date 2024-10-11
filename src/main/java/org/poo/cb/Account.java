package org.poo.cb;

public interface Account {
    Currency getCurrency();
    double getAmount();
    boolean add(double sum);
    boolean extract(double sum);
}
