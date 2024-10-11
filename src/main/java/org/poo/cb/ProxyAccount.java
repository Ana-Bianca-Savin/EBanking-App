package org.poo.cb;

public class ProxyAccount implements Account {
    private final Account account;

    public ProxyAccount(Currency currency) {
        account = new RealAccount(currency);
    }
    public Currency getCurrency() {
        return account.getCurrency();
    }
    public double getAmount() {
        return account.getAmount();
    }

    public boolean add(double sum) {
        if (sum >= 0)
            return account.add(sum);
        return false;
    }

    public boolean extract(double sum) {
        if (sum <= account.getAmount())
            return account.extract(sum);
        return false;
    }
}
