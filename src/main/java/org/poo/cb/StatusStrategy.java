package org.poo.cb;

public interface StatusStrategy {
    boolean exchange(Account acc, double amount);
    boolean buyStocks(Account acc, double amount);
}
