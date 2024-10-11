package org.poo.cb;

public class NonPremiumStrategy implements StatusStrategy {
    public boolean exchange(Account acc, double amount) {
        return acc.extract(amount + 0.01f * amount);
    }

    public boolean buyStocks(Account acc, double amount) {
        return acc.extract(amount);
    }
}
