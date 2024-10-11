package org.poo.cb;

import java.util.LinkedHashMap;

public class Portfolio {
    //  Linked hashmap for retaining all accounts the user has
    private final LinkedHashMap<Currency, Account> accounts = new LinkedHashMap<Currency, Account>();
    //  Linked hashmap for retaining stock's name and amount the user has
    private final LinkedHashMap<String, Integer> stocks = new LinkedHashMap<String, Integer>();

    public LinkedHashMap<Currency, Account> getAccounts() {
        return accounts;
    }

    public LinkedHashMap<String, Integer> getStocks() {
        return stocks;
    }

    public void addAccount(ProxyAccount acc) {
        accounts.put(acc.getCurrency(), acc);
    }
}
