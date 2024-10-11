package org.poo.cb;

import java.util.HashMap;

public class ListPortfolioCommand extends Command {
    final private String email;

    public ListPortfolioCommand(String email) {
        this.email = email;
    }

    public void execute(Bank bank) {
        //  Get the user
        User user = bank.getUsers().get(this.email);

        //  Check if the user exists
        if (user == null) {
            System.out.println("User with " + this.email + " doesn't exist");
            return;
        }

        //  Get the two hashmaps containing data
        HashMap<Currency, Account> accounts = user.getPortfolio().getAccounts();
        HashMap<String, Integer> stocks = user.getPortfolio().getStocks();

        //  Build the string containing information about stocks
        String stockData = "";
        int cnt = 0;
        for (String st: stocks.keySet()) {
            stockData += "{\"stockName\":\"" + st + "\",\"amount\":" + stocks.get(st) + "}";
            if (cnt != stocks.size() - 1)
                stockData += ",";
            cnt++;
        }

        //  Build the string containing information about accounts
        String accData = "";
        cnt = 0;
        for (Account acc: accounts.values()) {
            accData += "{\"currencyName\":\"" + acc.getCurrency() + "\",\"amount\":\"" + String.format("%.2f", acc.getAmount()) + "\"}";
            if (cnt != accounts.size() - 1)
                accData += ",";
            cnt++;
        }

        //  Display the result
        System.out.println("{\"stocks\":[" + stockData + "],\"accounts\":[" + accData + "]}");
    }
}
