package org.poo.cb;

public class RealAccount implements Account {
    private final Currency currency;
    private double amount;

    public RealAccount(Currency currency) {
        this.currency = currency;
        this.amount = 0;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    public boolean add(double sum) {
        this.amount += sum;
        return true;
    }

    public boolean extract(double sum) {
        this.amount -= sum;
        return true;
    }
}
