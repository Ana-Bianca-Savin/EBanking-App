package org.poo.cb;

import java.util.ArrayList;

public class BuyStocksCommand extends Command {
    final private String email;
    final private String company;
    final private int noOfStocks;

    public BuyStocksCommand(String email, String company, int noOfStocks) {
        this.email = email;
        this.company = company;
        this.noOfStocks = noOfStocks;
    }

    public void execute(Bank bank) {
        //  Get the user
        User user = bank.getUsers().get(this.email);

        //  Check if the user exists
        if (user == null) {
            System.out.println("User with " + this.email + " doesn't exist");
            return;
        }

        //  Get the stock and calculate the price
        Stock stock = bank.getStocks().get(this.company);
        double price = stock.getLast10Values()[9] * this.noOfStocks;

        //  Get the recommended stocks
        ArrayList<String> recommendedStocks = RecommendStocksCommand.getStringsForStocks(bank.getStocks());

        //  Determine if the 5% discount if applied
        StatusStrategy statusStrategy;
        if (user.getHasPremium() && recommendedStocks.contains(this.company))
            statusStrategy = new PremiumStrategy();
        else
            statusStrategy = new NonPremiumStrategy();

        //  Try to extract money
        boolean extractedSuccessfully = statusStrategy.buyStocks(user.getPortfolio().getAccounts().get(Currency.USD), price);
        if (!extractedSuccessfully) {
            System.out.println("Insufficient amount in account for buying stock");
            return;
        }

        //  Add the stock to portfolio
        user.getPortfolio().getStocks().put(this.company, this.noOfStocks);
    }
}
