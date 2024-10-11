package org.poo.cb;

public class PremiumStrategy implements StatusStrategy {
    public boolean exchange(Account acc, double amount) {
        return acc.extract(amount);
    }

    public boolean buyStocks(Account acc, double amount) {
        return acc.extract(amount - 0.05 * amount);
    }
}
